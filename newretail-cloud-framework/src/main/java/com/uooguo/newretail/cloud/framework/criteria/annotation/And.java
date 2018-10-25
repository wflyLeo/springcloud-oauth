package com.uooguo.newretail.cloud.framework.criteria.annotation;

import java.lang.annotation.*;

/**
 * @author Tiangel
 * @date 2018-4-19
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface And {
    String value() default "";
}
