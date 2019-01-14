package com.hosmos.management.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 *
 * @author chenhuayang
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        ParameterBuilder builder = new ParameterBuilder();
        // 在swagger里显示header
        builder.parameterType("header").name("login-token").description("restful方式的header参数").required(false).modelRef(new ModelRef("string"));
        return new Docket(DocumentationType.SWAGGER_2).groupName("swagger接口文档").apiInfo(new ApiInfoBuilder().title("swagger接口文档").contact(new Contact("Hosmos", "", "")).version("1.0").build()).globalOperationParameters(Lists.newArrayList(builder.build())).select().paths(PathSelectors.any()).build();
    }
}