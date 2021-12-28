package com.wk.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableSwaggerBootstrapUI
@Configuration
@Slf4j
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //  通过环境监听查看自己是否处在设置的环境之中
        boolean flag = environment.acceptsProfiles(profiles);

        log.info("swagger启用状态:{}", flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(appName)

                //enable如果为false，则swagger就不能在浏览器中访问
                .enable(flag)
                .select()
                //select和build是一套，apis和paths只能在select和build之间来配置
                //RequestHandlerSelectors  配置要扫描接口的方式
                //RequestHandlerSelectors.withClassAnnotation()扫描类上的注解需要传入一个反射出来的类
                //RequestHandlerSelectors.withMethodAnnotation()扫描方法上的注解
                //因为有一些注解是用在类上的，有一些是放在方法上的比如@Controller这个注解就只能放在类上因
                // 此它只能放在RequestHandlerSelectors.withClassAnnotation()中
//                RequestHandlerSelectors.withClassAnnotation(RestController.class)就只会扫描有
                // RestController注解的类
                .apis(RequestHandlerSelectors.basePackage("com.wk"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger文档说明")
                .licenseUrl("#")
                .description("该文档作为前后端接口适配的标准")
                .contact(new Contact("wk", "baidu.com", "103420080@qq.com"))
                .version("1.0")
                .build();
    }

}