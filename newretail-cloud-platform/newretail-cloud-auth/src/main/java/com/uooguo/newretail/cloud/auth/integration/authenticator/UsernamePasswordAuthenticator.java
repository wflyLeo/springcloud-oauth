package com.uooguo.newretail.cloud.auth.integration.authenticator;

import com.uooguo.newretail.cloud.auth.integration.IntegrationAuthentication;
import com.uooguo.newretail.cloud.framework.util.StringUtils;
import com.uooguo.newretail.cloud.uc.client.SysUserClient;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 默认登录处理
 *
 * @author Tiangel
 * @date 2018-3-31
 **/
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private SysUserClient sysUserClient;

    @Override
    public SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        SysUserAuthentication user = sysUserClient.findUserByUsername(integrationAuthentication.getUsername());
        return user;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }
}
