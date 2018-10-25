package com.uooguo.newretail.cloud.framework.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Tiangel
 * @date 2018-4-3
 **/
@Data
@ConfigurationProperties(prefix = "newretail.swagger2")
public class Swagger2Properties {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String apiName;
    private String apiKeyName;
    private String termsOfServiceUrl;

}
