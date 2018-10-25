package com.uooguo.newretail.cloud.framework.security.access;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启安全访问，根据UPM中权限进行判断
 * @author Tiangel
 * @date 2018-4-11
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SecurityAccessAutoConfiguration.class)
public @interface EnableSecurityAccess {
}
