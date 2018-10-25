package com.uooguo.newretail.cloud.auth.integration.authenticator;

import com.uooguo.newretail.cloud.auth.integration.IntegrationAuthentication;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;

/**
 * @author Tiangel
 * @date 2018-4-4
 **/
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {

    @Override
    public abstract SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
