package com.uooguo.newretail.cloud.framework.oauth2.feign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Tiangel
 * @date 2018-3-14
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OAuth2ClientFeignConfiguration.class)
public @interface EnableOAuth2ClientFeign {
}
