package com.yingxue.lesson.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CustomRealm
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public class CustomRealm  extends AuthorizingRealm {
    /**
     * mock 用户信息
     * @param principalCollection
     * @return
     */
    private Map<String,String> userMap=new HashMap<>();
    {
        userMap.put("admin","123456");
        userMap.put("test","123456");
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username= (String) principalCollection.getPrimaryPrincipal();
        List<String> roles=getRolesByUserName(username);
        List<String> permissions=getPermissionsByUsername(username);
        //把数据交还给授权器
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password=getPasswordByUsername(username);
        if(StringUtils.isEmpty(password)){
            return null;
        }
//        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,password,getName());
//        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,getEncPassword(password),getName());
        //把数据交还给认证器
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,getEncPassword(password,username),getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(username));
        return info;

    }

//    private String getEncPassword(String password){
//        return new Md5Hash(password,null,3).toString();
//    }

    private String getEncPassword(String password,String salt){
        return new Md5Hash(password,salt,3).toString();
    }
    /**
     * 通过用户名获取密码
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param username
     * @return       java.lang.String
     * @throws
     */
    private String getPasswordByUsername(String username){

        return userMap.get(username);
    }




    /**
     * 通过用户名获取用户拥有角色信息
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param username
     * @return       java.util.List<java.lang.String>
     * @throws
     */
    private List<String> getRolesByUserName(String username){

        List<String> roles=new ArrayList<>();
        if(username.equals("admin")){
            roles.add("admin");
        }
        roles.add("test");
        return roles;
    }
    /**
     * 通过用户名获取用户拥有权限信息
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param username
     * @return       java.util.List<java.lang.String>
     * @throws
     */
    private List<String> getPermissionsByUsername(String username){

        List<String> permissions=new ArrayList<>();
        if(username.equals("admin")){
            permissions.add("*");
        }
        permissions.add("user:list");
        permissions.add("user:deleted");
        permissions.add("user:edit");
        return permissions;
    }
}
