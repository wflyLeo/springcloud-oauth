package com.uooguo.newretail.cloud.framework.criteria.annotation.group;

import com.uooguo.newretail.cloud.framework.criteria.annotation.OrderBy;

import java.lang.annotation.*;

/**
 * @author Tiangel
 * @date 2018-4-18
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderByGroup {

    OrderBy[] value();
}
