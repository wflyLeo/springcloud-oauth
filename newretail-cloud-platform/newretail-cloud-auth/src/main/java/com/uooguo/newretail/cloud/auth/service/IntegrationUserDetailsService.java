package com.uooguo.newretail.cloud.auth.service;

import com.uooguo.newretail.cloud.auth.integration.IntegrationAuthentication;
import com.uooguo.newretail.cloud.auth.integration.IntegrationAuthenticationContext;
import com.uooguo.newretail.cloud.auth.integration.authenticator.IntegrationAuthenticator;
import com.uooguo.newretail.cloud.framework.util.Assert;
import com.uooguo.newretail.cloud.framework.util.User;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import com.uooguo.newretail.cloud.upm.client.SysResourceClient;
import com.uooguo.newretail.cloud.upm.client.SysRoleClient;
import com.uooguo.newretail.cloud.upm.entity.SysResource;
import com.uooguo.newretail.cloud.upm.entity.SysRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集成认证用户服务
 *
 * @author Tiangel
 * @date 2018-3-7
 **/
@Service
public class IntegrationUserDetailsService implements UserDetailsService {

    @Resource
    private SysResourceClient sysResourceClient;

    @Resource
    private SysRoleClient sysRoleClient;

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
        this.setAuthorize(user);
        return user;

    }

    /**
     * 设置授权信息
     *
     * @param
     */
    public void setAuthorize(User user) {
        List<SysResource> resources = sysResourceClient.findMenuByUserId(user.getId());
        List<Long> resource = resources.stream().map(SysResource::getId).collect(Collectors.toList());

        List<SysRole> roles = sysRoleClient.findRoleByUserId(user.getId());
        List<Long> role = roles.stream().map(SysRole::getId).collect(Collectors.toList());

        user.setRoles(role);
        user.setResources(resource);
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
