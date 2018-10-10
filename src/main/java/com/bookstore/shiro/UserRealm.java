package com.bookstore.shiro;

import com.bookstore.bean.User;
import com.bookstore.common.UserManager;
import com.bookstore.enums.RoleTypeEnum;
import com.bookstore.service.UserService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) super.getAvailablePrincipal(principals);
        User user = userService.selectByUname(username.trim());
        if (user == null) return null;

        RoleTypeEnum roleTypeEnum = user.getRoleTypeEnum();
        List<String> roleList = new ArrayList<>();
        if (roleTypeEnum != null) roleList.add(roleTypeEnum.getDesc());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        System.err.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

        User user = userService.selectByUname(upToken.getUsername());
        if (user != null) {
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getMobile_phone());
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getMobile_phone(), user.getPassword(), credentialsSalt, String.valueOf(user.getId()));
            this.setSession(UserManager.CURRENT_USER, user);
            return authcInfo;
        }
        return null;
    }

    private void setSession(Object key, Object value) {
        Subject cureentUser = SecurityUtils.getSubject();
        if (cureentUser != null) {
            Session session = cureentUser.getSession();
            System.out.println("Session默认超时时间为[" + ((org.apache.shiro.session.Session) session).getTimeout() + "]毫秒");
            if (session != null) {
                ((org.apache.shiro.session.Session) session).setAttribute(key, value);
            }
        }
    }
}
