package com.uooguo.newretail.cloud.auth.service;

import com.uooguo.newretail.cloud.auth.integration.IntegrationAuthentication;
import com.uooguo.newretail.cloud.auth.integration.IntegrationAuthenticationContext;
import com.uooguo.newretail.cloud.auth.integration.authenticator.IntegrationAuthenticator;
import com.uooguo.newretail.cloud.framework.util.Assert;
import com.uooguo.newretail.cloud.framework.util.User;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 集成认证用户服务
 *
 * @author Tiangel
 * @date 2018-3-7
 **/
@Service
public class IntegrationUserDetailsService implements UserDetailsService {

    private List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public User loadUserByUsername(String username) {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        SysUserAuthentication sysUserAuthentication = this.authenticate(integrationAuthentication);

        if (sysUserAuthentication == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        Assert.forbidden(!sysUserAuthentication.getStatus(), "用户被禁用");

        User user = new User();
        BeanUtils.copyProperties(sysUserAuthentication, user);
        return user;

    }

    private SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
