package com.yingxue.lesson.service.impl;

import com.github.pagehelper.PageHelper;
import com.yingxue.lesson.constants.Constant;
import com.yingxue.lesson.entity.SysDept;
import com.yingxue.lesson.entity.SysUser;
import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.exception.code.BaseResponseCode;
import com.yingxue.lesson.mapper.SysDeptMapper;
import com.yingxue.lesson.mapper.SysUserMapper;
import com.yingxue.lesson.service.*;
import com.yingxue.lesson.utils.JwtTokenUtil;
import com.yingxue.lesson.utils.PageUtil;
import com.yingxue.lesson.utils.PasswordUtils;
import com.yingxue.lesson.utils.TokenSettings;
import com.yingxue.lesson.vo.req.*;
import com.yingxue.lesson.vo.resp.LoginRespVO;
import com.yingxue.lesson.vo.resp.PageVO;
import com.yingxue.lesson.vo.resp.UserOwnRoleRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UserserviceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenSettings tokenSettings;
    @Autowired
    private PermissionService permissionService;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        //通过用户名查询用户信息
        //如果查询存在用户
        //就比较它密码是否一样
        SysUser userInfoByName = sysUserMapper.getUserInfoByName(vo.getUsername());
        if(userInfoByName==null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(userInfoByName.getStatus()==2){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK_TIP);
        }
        if(!PasswordUtils.matches(userInfoByName.getSalt(),vo.getPassword(),userInfoByName.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO=new LoginRespVO();
        loginRespVO.setPhone(userInfoByName.getPhone());
        loginRespVO.setUsername(userInfoByName.getUsername());
        loginRespVO.setId(userInfoByName.getId());
        Map<String, Object> claims=new HashMap<>();
        claims.put(Constant.JWT_ROLES_KEY,getRolesByUserId(userInfoByName.getId()));
        claims.put(Constant.JWT_PERMISSIONS_KEY,getPermissionsByUserId(userInfoByName.getId()));
        claims.put(Constant.JWT_USER_NAME,userInfoByName.getUsername());
        String accessToken=JwtTokenUtil.getAccessToken(userInfoByName.getId(),claims);
        String refreshToken;
        if(vo.getType().equals("1")){
            refreshToken=JwtTokenUtil.getRefreshToken(userInfoByName.getId(),claims);
        }else {
            refreshToken=JwtTokenUtil.getRefreshAppToken(userInfoByName.getId(),claims);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        return loginRespVO;
    }
    /**
     * mock 数据
     * 通过用户id获取该用户所拥有的角色
     * 后期修改为通过操作DB获取
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param userId
     * @return       java.util.List<java.lang.String>
     * @throws
     */
    private List<String> getRolesByUserId(String userId){

        return  roleService.getRoleNames(userId);
    }
    /**
     * mock 数据
     * 通过用户id获取该用户所拥有的角色
     * 后期通过操作数据获取
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param userId
     * @return       java.util.List<java.lang.String>
     * @throws
     */
    private Set<String> getPermissionsByUserId(String userId){
        return  permissionService.getPermissionsByUserId(userId);
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        if(StringUtils.isEmpty(accessToken)||StringUtils.isEmpty(refreshToken)){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        log.info("subject.getPrincipals()={}",subject.getPrincipals());
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        String userId=JwtTokenUtil.getUserId(accessToken);
        /**
         * 把token 加入黑名单 禁止再访问我们的系统资源
         */
        redisService.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);


    }

    @Override
    public PageVO<SysUser> pageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysUser> list=sysUserMapper.selectAll(vo);
        for (SysUser sysUser:list){
            SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysUser.getDeptId());
            if(sysDept!=null){
                sysUser.setDeptName(sysDept.getName());
            }
        }
        return PageUtil.getPageVO(list);
    }

    @Override
    public void addUser(UserAddReqVO vo) {
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreateTime(new Date());
        String salt=PasswordUtils.getSalt();
        String ecdPwd=PasswordUtils.encode(vo.getPassword(),salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(ecdPwd);
        int i = sysUserMapper.insertSelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId) {
        UserOwnRoleRespVO respVO=new UserOwnRoleRespVO();
        respVO.setOwnRoles(userRoleService.getRoleIdsByUserId(userId));
        respVO.setAllRole(roleService.selectAll());
        return respVO;
    }

    @Override
    public void setUserOwnRole(UserOwnRoleReqVO vo) {
        userRoleService.addUserRoleInfo(vo);
        /**
         * 标记用户 要主动去刷新
         */
        redisService.set(Constant.JWT_REFRESH_KEY+vo.getUserId(),vo.getUserId(),tokenSettings.getAccessTokenExpireTime().toMillis(),TimeUnit.MILLISECONDS);
        /**
         * 清楚用户授权数据缓存
         */
        redisService.delete(Constant.IDENTIFY_CACHE_KEY+vo.getUserId());
    }

    @Override
    public String refreshToken(String refreshToken) {
        //它是否过期
        //它是否被加如了黑名
        if(!JwtTokenUtil.validateToken(refreshToken)||redisService.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        String userId=JwtTokenUtil.getUserId(refreshToken);
        log.info("userId={}",userId);
        Map<String,Object> claims=null;
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            claims=new HashMap<>();
            claims.put(Constant.JWT_ROLES_KEY,getRolesByUserId(userId));
            claims.put(Constant.JWT_PERMISSIONS_KEY,getPermissionsByUserId(userId));
        }
        String newAccessToken=JwtTokenUtil.refreshToken(refreshToken,claims);
//        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
//            redisService.set(Constant.JWT_REFRESH_IDENTIFICATION+newAccessToken,userId,
//                   redisService.getExpire(Constant.JWT_REFRESH_KEY+userId,TimeUnit.MILLISECONDS),TimeUnit.MILLISECONDS);
//        }
        return newAccessToken;
    }

    @Override
    public void updateUserInfo(UserUpdateReqVO vo, String operationId) {
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(operationId);
        if(StringUtils.isEmpty(vo.getPassword())){
            sysUser.setPassword(null);
        }else {
            String salt=PasswordUtils.getSalt();
            String endPwd=PasswordUtils.encode(vo.getPassword(),salt);
            sysUser.setSalt(salt);
            sysUser.setPassword(endPwd);
        }

        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        if(vo.getStatus()==2){
            redisService.set(Constant.ACCOUNT_LOCK_KEY+vo.getId(),vo.getId());
        }else {
            redisService.delete(Constant.ACCOUNT_LOCK_KEY+vo.getId());
        }
    }
    @Override
    public void deletedUsers(List<String> list, String operationId) {
        SysUser sysUser=new SysUser();
        sysUser.setUpdateId(operationId);
        sysUser.setUpdateTime(new Date());
        int i = sysUserMapper.deletedUsers(sysUser, list);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        for (String userId:
                list) {
            redisService.set(Constant.DELETED_USER_KEY+userId,userId,tokenSettings.getRefreshTokenExpireAppTime().toMillis(),TimeUnit.MILLISECONDS);
            /**
             * 清楚用户授权数据缓存
             */
            redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
        }
    }

    @Override
    public List<SysUser> selectUserInfoByDeptIds(List<String> deptIds) {
        return sysUserMapper.selectUserInfoByDeptIds(deptIds);
    }

    @Override
    public SysUser detailInfo(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo, String userId) {
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setId(userId);
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(userId);
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public void userUpdatePwd(UserUpdatePwdReqVO vo, String accessToken, String refreshToken) {
        String userId=JwtTokenUtil.getUserId(accessToken);
        //校验旧密码
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if(sysUser==null){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        if(!PasswordUtils.matches(sysUser.getSalt(),vo.getOldPwd(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.OLD_PASSWORD_ERROR);
        }
        //保存新密码
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateId(userId);
        sysUser.setPassword(PasswordUtils.encode(vo.getNewPwd(),sysUser.getSalt()));
        int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }

        /**
         * 把token 加入黑名单 禁止再访问我们的系统资源
         */
        redisService.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken,userId,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);

        /**
         * 清楚用户授权数据缓存
         */
        redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
    }
}
