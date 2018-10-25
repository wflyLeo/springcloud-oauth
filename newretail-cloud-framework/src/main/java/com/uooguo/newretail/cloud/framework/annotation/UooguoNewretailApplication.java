package com.uooguo.newretail.cloud.framework.annotation;

import com.uooguo.newretail.cloud.framework.oauth2.feign.EnableOAuth2ClientFeign;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.annotation.*;

/**
 * @author jiangskui
 * @date 2018/9/13  16:57
 * @description 优菓新零售服务注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@EnableAsync
@Configuration
@EnableResourceServer
@MapperScan("com.uooguo.**.mapper")
@ComponentScan("com.uooguo")
@EnableFeignClients("com.uooguo")
@SpringCloudApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableTransactionManagement
@EnableOAuth2Client
@EnableOAuth2ClientFeign
public @interface UooguoNewretailApplication {
}
