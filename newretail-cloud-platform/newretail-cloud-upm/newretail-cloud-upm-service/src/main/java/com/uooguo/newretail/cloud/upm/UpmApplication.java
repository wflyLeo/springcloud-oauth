package com.uooguo.newretail.cloud.upm;


import com.uooguo.newretail.cloud.framework.annotation.UooguoNewretailApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Tiangel
 * @date 2018-3-8
 **/
@UooguoNewretailApplication
public class UpmApplication extends ResourceServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(UpmApplication.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v2/api-docs", "/actuator/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }

}
