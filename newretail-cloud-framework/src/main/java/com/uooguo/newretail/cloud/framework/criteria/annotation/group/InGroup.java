package com.uooguo.newretail.cloud.framework.criteria.annotation.group;

import com.uooguo.newretail.cloud.framework.criteria.annotation.In;

import java.lang.annotation.*;

/**
 * @author Tiangel
 * @date 2018-4-18
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InGroup {

    In[] value();
}
