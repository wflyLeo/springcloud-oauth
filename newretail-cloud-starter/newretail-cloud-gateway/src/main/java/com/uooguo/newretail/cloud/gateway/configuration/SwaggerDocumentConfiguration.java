package com.uooguo.newretail.cloud.gateway.configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiangel
 * @date 2018-3-8
 **/
@Component
@Primary
public class SwaggerDocumentConfiguration implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("认证中心", "/api/auth/v2/api-docs", "2.0"));
        resources.add(swaggerResource("用户中心", "/api/uc/v2/api-docs", "2.0"));
        resources.add(swaggerResource("权限系统", "/api/upm/v2/api-docs", "2.0"));
        resources.add(swaggerResource("登陆服务", "/api/login/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
