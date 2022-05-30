package com.wk.gateway.swagger;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {
    /**
     * swagger2默认的url后缀
     */
    private static final String SWAGGER2URL = "/v2/api-docs?group=";

    // private static final String OAS_30_URL = "/v3/api-docs";

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String self;

    @Value("#{'${knife4j.excludeList}'.split(',')}")
    private List<String> excludeSwaggerAppList;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        List<String> services = discoveryClient.getServices();
        Optional.ofNullable(services).orElseThrow(() -> new RuntimeException("注册中心服务为空"));
        log.info("排除swagger的应用列表:{},本身:{}", excludeSwaggerAppList, self);
        services.stream().distinct().forEach(serviceName -> {
            if (self.equalsIgnoreCase(serviceName)) {
                //不路由本身
                return;
            }
            if (excludeSwaggerAppList.contains(serviceName)) {
                return;
            }
            String url = "/" + serviceName + SWAGGER2URL + serviceName;
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setUrl(url);
            swaggerResource.setName(serviceName);
            swaggerResource.setSwaggerVersion("2.9.2");
            resources.add(swaggerResource);
        });
        return resources;
    }

}

