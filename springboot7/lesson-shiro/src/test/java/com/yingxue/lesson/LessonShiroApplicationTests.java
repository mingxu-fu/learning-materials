package com.yingxue.lesson;

import com.alibaba.druid.pool.DruidDataSource;
import com.yingxue.lesson.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonShiroApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void authentication() {
        //构建一个安全管理器环境
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //数据源
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("zhangsan", "666666");//写死的形式
        //把域设置到安全管理器内
        defaultWebSecurityManager.setRealm(simpleAccountRealm);
        //把安全管理器配置到工具包里面
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        //至此第一步完成


        //提交认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan1", "666666");

        try {
            subject.login(usernamePasswordToken);

            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }
    }





    @Test
    public void authorization(){
        //构建一个安全管理器环境
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("zhangsan", "666666","admin","test");
        defaultWebSecurityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);


        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "666666");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.checkRoles("admin","test");
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }catch (UnauthorizedException e){
            System.out.println("该用户没有权限访问");
        }
    }

    @Test
    public void testIniRealm(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        IniRealm iniRealm=new IniRealm("classpath:shiro.ini");
        defaultWebSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);

        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test", "123456");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.checkRoles("test");
            subject.checkPermissions("user:list");
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }catch (UnauthorizedException e){
            System.out.println("该用户没有权限访问");
        }
    }


    @Test
    public void testJdbcRealm(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        JdbcRealm jdbcRealm=new JdbcRealm();
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("rootroot");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        必须 要有这句胡,表示开启shiro权限校验
        jdbcRealm.setPermissionsLookupEnabled(true);

        //动态的修改自定义数据库表名
         String authenticationQuery = "select password from sys_users where username = ?";
         String userRolesQuery = "select role_name from sys_user_roles where username = ?";
         String permissionsQuery = "select permission from sys_roles_permissions where role_name = ?";
         jdbcRealm.setAuthenticationQuery(authenticationQuery);
         jdbcRealm.setUserRolesQuery(userRolesQuery);
         jdbcRealm.setPermissionsQuery(permissionsQuery);


        jdbcRealm.setDataSource(druidDataSource);
        defaultWebSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("test", "123456");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.checkRoles("test");
            subject.checkPermissions("user:deleted","user:list");
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }catch (UnauthorizedException e){
            System.out.println("该用户没有权限访问");
        }
    }


    @Test
    public void testCustomRealm(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        CustomRealm customRealm=new CustomRealm();
        defaultWebSecurityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123456");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.checkRoles("test");
            subject.checkPermissions("user:deleted","user:list","role:list");
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }catch (UnauthorizedException e){
            System.out.println("该用户没有权限访问");
        }
    }


    @Test
    public void testMatcher(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        CustomRealm customRealm=new CustomRealm();

        //进行加密
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        //加密方式md5(不可逆的)
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(3);
        customRealm.setCredentialsMatcher(matcher);
        defaultWebSecurityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123456");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.checkRoles("test");
            subject.checkPermissions("user:deleted","user:list","role:list");
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }catch (UnauthorizedException e){
            System.out.println("该用户没有权限访问");
        }
    }


    @Test
    public void testSaltMatcher(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        CustomRealm customRealm=new CustomRealm();
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(3);
        customRealm.setCredentialsMatcher(matcher);
        defaultWebSecurityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123456");

        try {
            subject.login(usernamePasswordToken);
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            subject.checkRoles("test");
            subject.checkPermissions("user:deleted","user:list","role:list");
            subject.logout();
            System.out.println("用户认证的状态" + subject.isAuthenticated());
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户密码不匹配");
        } catch (LockedAccountException lae) {
            System.out.println("账号一杯锁定");

        } catch (AuthenticationException ae) {
            System.out.println("用户认证异常");
        }catch (UnauthorizedException e){
            System.out.println("该用户没有权限访问");
        }
    }
}
