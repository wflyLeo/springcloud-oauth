package com.uooguo.newretail.cloud.framework.base.entity;

import java.io.Serializable;

/**
 * @author Tiangel
 * @date 2018-1-11
 **/
public interface TenantEntity extends Serializable {
    /**
     * 获取ID
     *
     * @return
     */
    Long getId();

    /**
     * 获取管理员ID
     *
     * @return
     */
    Long getAdministrator();
}
