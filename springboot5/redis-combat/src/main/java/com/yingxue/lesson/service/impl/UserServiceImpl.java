package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.contants.Contant;
import com.yingxue.lesson.entity.SysUser;
import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.mapper.SysUserMapper;
import com.yingxue.lesson.service.RedisService;
import com.yingxue.lesson.service.UserService;
import com.yingxue.lesson.utils.PasswordUtils;
import com.yingxue.lesson.vo.req.LoginReqVO;
import com.yingxue.lesson.vo.req.RegisterReqVO;
import com.yingxue.lesson.vo.resp.LoginRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: UserServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisService redisService;
    @Override
    public LoginRespVO login(LoginReqVO vo) {
        SysUser sysUser = sysUserMapper.selectByUsername(vo.getUsername());
        if(sysUser==null){
            throw new BusinessException(4001005,"不存在该用户，请先注册");
        }
        if(sysUser.getStatus()==2){
            throw new BusinessException(4001006,"该账号已被禁用请联系系统管理员");
        }
        if(!PasswordUtils.matches(sysUser.getSalt(),vo.getPassword(),sysUser.getPassword())){
            throw new BusinessException(4001007,"用户名密码不匹配");
        }
        String token= UUID.randomUUID().toString();
        LoginRespVO respVO=new LoginRespVO();
        respVO.setUserId(sysUser.getId());
        respVO.setToken(token);
        redisService.set(token,sysUser.getId(),60, TimeUnit.MINUTES);
        redisService.set(sysUser.getId(),token,60,TimeUnit.MINUTES);
        return respVO;
    }

    @Override
    public SysUser getUserInfo(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public String register(RegisterReqVO vo) {

        //判断验证码是否有效
        if(!redisService.hasKey(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+vo.getPhone())){
            throw new BusinessException(4001008,"验证码已失效请重新获取");
        }
        //校验验证码是否正确
        if(!vo.getCode().equals(redisService.get(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+vo.getPhone()))){
         throw new BusinessException(4001009,"请输入正确的验证码");
        }
        SysUser sysUser = sysUserMapper.selectByUsername(vo.getUsername());
        if(sysUser!=null){
            throw new BusinessException(4001010,"该账号已被占用");
        }
        SysUser user=new SysUser();
        BeanUtils.copyProperties(vo,user);
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        String salt=PasswordUtils.getSalt();
        String ecdPwd=PasswordUtils.encode(vo.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(ecdPwd);
        int i = sysUserMapper.insertSelective(user);
        if(i!=1){
            throw new BusinessException(4001011,"操作失败");
        }
        redisService.delete(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+vo.getPhone());
        redisService.delete(Contant.REGISTER_CODE_COUNT_KEY+vo.getPhone());

        return "注册成功";
    }

    @Override
    public String getCode(String phone) {
        //验证手机号是否合法
        Pattern pattern = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()) {
            throw  new BusinessException(4001004,"手机号格式错误");
        }
        //判断手机号是否超限
        long count = redisService.incrby(Contant.REGISTER_CODE_COUNT_KEY+phone,1);
        if(count>5){
            throw new BusinessException(4001004,"当日发送已达上限");
        }

        //生成6位随机数
        String code=generateCode();
        //发送短信(具体根据你们公司所用的api文档来)
        //存入 redis 过期时间为 5 分钟
        redisService.set(Contant.REGISTER_CODE_COUNT_VALIDITY_KEY+phone,code,5,TimeUnit.MINUTES);
        //发送短信这里用输出模拟
        System.out.println(code);
        return code;
    }

    /**
     * 生成六位验证码
     * @return
     */
    private String generateCode(){
        Random random = new Random();
        int x = random.nextInt(899999);
        String code = String.valueOf(x + 100000);
        return code;
    }
}
