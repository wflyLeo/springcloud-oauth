package com.uooguo.newretail.cloud.framework.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Throwable cause = authException.getCause();
        map.put("code", response.SC_UNAUTHORIZED);//401
        map.put("data", authException.getMessage());
        map.put("path", request.getRequestURI());
        map.put("timestamp", new Date());

        String msg = "";
        if (cause instanceof InvalidTokenException) {
            msg = "无效的token";
        } else {
            msg = "访问此资源需要完全的身份验证";
        }
        Result result = Result.build(false, msg, -99, map);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), result);
    }
}
