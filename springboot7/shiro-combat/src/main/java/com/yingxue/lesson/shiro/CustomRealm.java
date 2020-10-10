package com.yingxue.lesson.shiro;

import com.yingxue.lesson.service.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CustomRealm
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Autowired
    private RedisService redisService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token= (String) principalCollection.getPrimaryPrincipal();
        String userId= (String) redisService.get(token);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(getRoleByUserId(userId));
        info.addStringPermissions(getPermissionsByUserId(userId));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomUsernamePasswordToken token= (CustomUsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
        return info;
    }

    private List<String> getRoleByUserId(String userId){
        List<String> roles=new ArrayList<>();
        if(userId.equals("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8")){
            roles.add("admin");
        }
        roles.add("test");
        return roles;
    }

    private List<String> getPermissionsByUserId(String userId){
        List<String> permissions=new ArrayList<>();
        if(userId.equals("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8")){
            permissions.add("*");
        }
        permissions.add("user:list");
        permissions.add("user:edit");
        return permissions;
    }
}
