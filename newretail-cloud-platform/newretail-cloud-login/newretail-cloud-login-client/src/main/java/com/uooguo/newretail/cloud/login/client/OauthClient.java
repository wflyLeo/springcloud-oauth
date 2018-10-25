package com.uooguo.newretail.cloud.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "auth-service")
public interface OauthClient {
    @PostMapping(path = "/auth/oauth/token")
    Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);

}
