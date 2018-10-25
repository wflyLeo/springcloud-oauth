package com.uooguo.newretail.cloud.framework.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本地存储属性配置
 *
 * @author Tiangel
 * @date 2017-12-19
 */
@ConfigurationProperties(prefix = "newretail.storage.local" )
public class LocalStorageProperties {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
