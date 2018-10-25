package com.uooguo.newretail.cloud.login.service.service.impl;

import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.login.client.OauthClient;
import com.uooguo.newretail.cloud.login.service.service.LoginService;
import com.uooguo.newretail.cloud.uc.client.SysUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2018-09-11
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private OauthClient oauthClient;

    @Override
    public Map<String, Object> login(HttpServletRequest request, String username, String password) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", request.getHeader("grantType"));
        parameters.put("client_id", request.getHeader("clientId"));
        parameters.put("client_secret", request.getHeader("clientSecret"));
        parameters.put("scope", request.getHeader("scope"));
        parameters.put("username", username);
        parameters.put("password", password);
        Map<String, Object> tokenInfo = oauthClient.postAccessToken(parameters);
        return tokenInfo;
    }

}
