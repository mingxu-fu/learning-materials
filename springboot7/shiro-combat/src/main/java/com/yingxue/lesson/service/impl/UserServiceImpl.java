package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.entity.SysUser;
import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.mapper.SysUserMapper;
import com.yingxue.lesson.service.RedisService;
import com.yingxue.lesson.service.UserService;
import com.yingxue.lesson.utils.PasswordUtils;
import com.yingxue.lesson.vo.req.LoginReqVO;
import com.yingxue.lesson.vo.resp.LoginRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        SysUser userByUsername = sysUserMapper.getUserByUsername(vo.getUsername());
        if(userByUsername==null){
            throw new BusinessException(4001004,"用户不存在，请注册");
        }
        if(userByUsername.getStatus()==2){
            throw new BusinessException(4001005,"该用户已被禁用，请联系系统管理员");
        }

        if(!PasswordUtils.matches(userByUsername.getSalt(),vo.getPassword(),userByUsername.getPassword())){
            throw new BusinessException(4001006,"用户名密码不匹配，请从新登录");
        }
        LoginRespVO respVO=new LoginRespVO();
        respVO.setUserId(userByUsername.getId());
        String token= UUID.randomUUID().toString();
        respVO.setToken(token);
        redisService.set(token,userByUsername.getId(),60, TimeUnit.MINUTES);

        return respVO;
    }

    @Override
    public SysUser detail(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
}
