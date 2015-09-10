package com.xie.springcase.realm;

import com.xie.springcase.hibernate.entity.SysFunct;
import com.xie.springcase.hibernate.entity.SysRole;
import com.xie.springcase.hibernate.entity.SysUser;
import com.xie.springcase.service.ISysRoleService;
import com.xie.springcase.service.ISysUserService;
import com.xie.springcase.service.impl.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqinchao on 15-9-7.
 */
@Service
@Transactional
public class MyRealm extends AuthorizingRealm {
    @Resource(name="sysUserService")
    private ISysUserService sysUserService;
    @Resource(name="sysRoleService")
    private ISysRoleService sysRoleService;

    /**
     * 为当前登录的Subject授予角色和权限
     *
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String) super.getAvailablePrincipal(principals);
        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        //从数据库中获取当前登录用户的详细信息
        SysUser user = sysUserService.findByName(currentUsername);
        if (null != user) {
            //实体类User中包含有用户角色的实体类信息
            if (null != user.getSysRoles() && user.getSysRoles().size() > 0) {
                //获取当前登录用户的角色
                for (SysRole role : user.getSysRoles()) {
                    roleList.add(role.getName());
                    //实体类Role中包含有角色权限的实体类信息
                    if (null != role.getSysFuncts() && role.getSysFuncts().size() > 0) {
                        //获取权限
                        for (SysFunct funct : role.getSysFuncts()) {
                            if (!StringUtils.isEmpty(funct.getName())) {
                                permissionList.add(funct.getName());
                            }
                        }
                    }
                }
            }
        } else {
            throw new AuthorizationException();
        }
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        simpleAuthorInfo.addRoles(roleList);
        simpleAuthorInfo.addStringPermissions(permissionList);
        return simpleAuthorInfo;
        /*
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得
        if (null != currentUsername && "mike".equals(currentUsername)) {
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            simpleAuthorInfo.addRole("admin");
            //添加权限
            simpleAuthorInfo.addStringPermission("admin:manage");
            System.out.println("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
            return simpleAuthorInfo;
        } */
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        //return null;
    }


    /**
     * 验证当前登录的Subject
     *
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        SysUser user = sysUserService.findByName(token.getUsername());
        if (null != user) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), user.getEmployee().getName());
            this.setSession("currentUser", user);
            return authcInfo;
        } else {
            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            return null;
        }
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
