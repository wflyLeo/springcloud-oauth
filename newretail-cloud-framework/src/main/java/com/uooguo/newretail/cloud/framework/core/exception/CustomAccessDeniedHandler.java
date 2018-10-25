package com.uooguo.newretail.cloud.framework.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uooguo.newretail.cloud.framework.core.constant.ErrorStatus;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        map.put("code", response.SC_UNAUTHORIZED);//401
        map.put("data", accessDeniedException.getMessage());
        map.put("path", request.getRequestURI());
        map.put("timestamp", new Date());

        Result result = Result.build(false, ErrorStatus.UNAUTHORIZED.getMessage(), -99, map);

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(result));
    }
}
