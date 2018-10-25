package com.uooguo.newretail.cloud.framework.security.access;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tiangel
 * @date 2018-4-10
 **/
@Slf4j
public class SecurityAccessAutoConfiguration {

    @Resource
    private CacheManager cacheManager;

    @Value("${spring.application.name:}")
    private String serviceId;


    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v2/api-docs","/actuator/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }


    @Bean("securityAccessMetadataSource")
    public FilterInvocationSecurityMetadataSource securityAccessMetadataSource() {
        SecurityAccessMetadataSource securityAccessMetadataSource = new SecurityAccessMetadataSource();
        securityAccessMetadataSource.setCacheManager(cacheManager);
        securityAccessMetadataSource.setServiceId(serviceId);
        return securityAccessMetadataSource;
    }

    @Bean
    public FilterSecurityInterceptor securityAccessInterceptor() {
        FilterSecurityInterceptor securityInterceptor = new FilterSecurityInterceptor();
        securityInterceptor.setAccessDecisionManager(new SecurityAccessDecisionManager());
        securityInterceptor.setSecurityMetadataSource(this.securityAccessMetadataSource());
        return securityInterceptor;
    }

    @Configuration
    @Conditional(EnableResourceServerCondition.class)
    private class ResourceServerSecurityAccessConfiguration extends ResourceServerConfigurerAdapter {

        public ResourceServerSecurityAccessConfiguration() {

        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            //排除Swagger文档
            http.authorizeRequests().antMatchers("/v2/api-docs","/actuator/**").permitAll().and().csrf().disable()
                    .authorizeRequests().anyRequest().authenticated().filterSecurityInterceptorOncePerRequest(false)
                    .and().addFilterAfter(securityAccessInterceptor(), FilterSecurityInterceptor.class);
            log.info("Security Access Control is enabled on Resource Server Application");
        }

    }

    @Configuration
    @Conditional(EnableWebSecurityCondition.class)
    private class WebSecurityAccessConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationManagerBuilder authenticationBuilder;
        @Autowired
        private ApplicationContext context;

        public WebSecurityAccessConfiguration() {

        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return new WebSecurityAccessConfiguration.AuthenticationManagerDelegator(this.authenticationBuilder, this.context);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //排除Swagger文档
            http.authorizeRequests().antMatchers("/v2/api-docs","/actuator/**").permitAll().and().csrf().disable()
                    .authorizeRequests().anyRequest().authenticated().filterSecurityInterceptorOncePerRequest(false)
                    .and().addFilterAfter(securityAccessInterceptor(), FilterSecurityInterceptor.class);
            log.info("Security Access Control is enabled on Web Application");
        }

        final class AuthenticationManagerDelegator implements AuthenticationManager {
            private AuthenticationManagerBuilder delegateBuilder;
            private AuthenticationManager delegate;
            private final Object delegateMonitor = new Object();
            private Set<String> beanNames;

            AuthenticationManagerDelegator(AuthenticationManagerBuilder delegateBuilder, ApplicationContext context) {
                Assert.notNull(delegateBuilder, "delegateBuilder cannot be null");
                Field parentAuthMgrField = ReflectionUtils.findField(AuthenticationManagerBuilder.class, "parentAuthenticationManager");
                ReflectionUtils.makeAccessible(parentAuthMgrField);
                this.beanNames = getAuthenticationManagerBeanNames(context);
                validateBeanCycle(ReflectionUtils.getField(parentAuthMgrField, delegateBuilder), this.beanNames);
                this.delegateBuilder = delegateBuilder;
            }

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                if (this.delegate != null) {
                    return this.delegate.authenticate(authentication);
                } else {
                    Object var2 = this.delegateMonitor;
                    synchronized(this.delegateMonitor) {
                        if (this.delegate == null) {
                            this.delegate = (AuthenticationManager)this.delegateBuilder.getObject();
                            this.delegateBuilder = null;
                        }
                    }

                    return this.delegate.authenticate(authentication);
                }
            }

            private  Set<String> getAuthenticationManagerBeanNames(ApplicationContext applicationContext) {
                String[] beanNamesForType = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, AuthenticationManager.class);
                return new HashSet(Arrays.asList(beanNamesForType));
            }

            private  void validateBeanCycle(Object auth, Set<String> beanNames) {
                if (auth != null && !beanNames.isEmpty()) {
                    if (auth instanceof Advised) {
                        Advised advised = (Advised)auth;
                        TargetSource targetSource = advised.getTargetSource();
                        if (targetSource instanceof LazyInitTargetSource) {
                            LazyInitTargetSource lits = (LazyInitTargetSource)targetSource;
                            if (beanNames.contains(lits.getTargetBeanName())) {
                                throw new FatalBeanException("A dependency cycle was detected when trying to resolve the AuthenticationManager. Please ensure you have configured authentication.");
                            }
                        }
                    }

                    beanNames = Collections.emptySet();
                }

            }
        }
    }



}
