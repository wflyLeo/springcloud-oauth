package com.uooguo.newretail.cloud.framework.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author jiangskui
 * @date 2018/9/25  18:07
 * @description TODO
 */
public class FeignInterceptor implements RequestInterceptor{

    @Value("${uooguo-newretail-cloud-common.feign.proxy.header:Cookie,Authorization}")
    private String[] proxyHeader;

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional.ofNullable(attributes)
                .ifPresent(attr -> Arrays
                        .stream(proxyHeader)
                        .forEach(s -> template.header(s, attr.getRequest().getHeader(s))));

    }
}
