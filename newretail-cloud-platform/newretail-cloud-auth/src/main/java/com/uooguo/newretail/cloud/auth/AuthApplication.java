package com.uooguo.newretail.cloud.auth;


import com.uooguo.newretail.cloud.framework.oauth2.feign.EnableOAuth2ClientFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Tiangel
 */
@EnableWebSecurity
@SpringCloudApplication
@EnableResourceServer
@EnableCaching
@EnableOAuth2Client
@EnableOAuth2ClientFeign
@EnableFeignClients("com.uooguo")
@ComponentScan("com.uooguo")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
