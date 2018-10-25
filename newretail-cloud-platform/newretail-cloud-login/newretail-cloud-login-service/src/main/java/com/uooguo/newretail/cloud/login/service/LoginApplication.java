package com.uooguo.newretail.cloud.login.service;

import com.uooguo.newretail.cloud.framework.annotation.UooguoNewretailApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@UooguoNewretailApplication
public class LoginApplication extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //TODO 需要加权限校验
        http.authorizeRequests()
                .antMatchers("/v2/api-docs", "/actuator/**", "/login").permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
        //http.authorizeRequests().anyRequest().permitAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

}
