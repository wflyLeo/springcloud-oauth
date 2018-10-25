package com.uooguo.newretail.cloud.framework.config;

import com.uooguo.newretail.cloud.framework.interceptor.FeignInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author jiangskui
 * @date 2018/9/25  18:11
 * @description TODO
 */
//@Configuration
//@ConditionalOnClass(name = "feign.RequestInterceptor")
public class FeignClientConfigurer {


    @Bean
    @ConditionalOnProperty(value = "uooguo-newretail-cloud-common.feign.proxy.enabled", havingValue = "true")
    @ConditionalOnMissingBean
    public RequestInterceptor feignInterceptor() {
        return new FeignInterceptor();
    }

    @Bean
    @ConditionalOnProperty(value = "uooguo-newretail-cloud-common.feign.debug", havingValue = "true")
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
