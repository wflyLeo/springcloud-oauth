package com.uooguo.newretail.cloud.framework.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Tiangel
 * @date 2018-1-18
 **/
@Data
public class BaseEntity {
    /**
     * 创建者
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改者
     */
    private Long modifyBy;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 发生修改IP
     */
    private String modifyIp;
}
