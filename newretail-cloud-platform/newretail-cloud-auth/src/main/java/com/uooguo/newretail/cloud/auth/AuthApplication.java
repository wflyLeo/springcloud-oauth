package com.uooguo.newretail.cloud.auth;


import com.uooguo.newretail.cloud.framework.annotation.UooguoNewretailApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author Tiangel
 */
@EnableWebSecurity
@UooguoNewretailApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
