package com.uooguo.newretail.cloud.auth.endpoint;

import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.framework.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登出接口
 *
 * @author Tiangel
 * @date 2018-3-26
 **/
@FrameworkEndpoint
@Api(tags = "登出接口")
public class TokenRevokeEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @PostMapping("/oauth/remove")
    @ApiOperation("退出登录")
    @ResponseBody
    public Result removeToken(@RequestHeader("Authorization") String access_token) {
        if (StringUtils.isNoneBlank(access_token)) {
            access_token = access_token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
        }else {
            return Result.buildFailure("认证失败");
        }
        if (consumerTokenServices.revokeToken(access_token)) {
            return Result.buildSuccess("注销成功");
        } else {
            return Result.buildFailure("注销失败");
        }
    }
}
