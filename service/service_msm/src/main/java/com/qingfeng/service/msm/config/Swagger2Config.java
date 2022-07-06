package com.qingfeng.service.msm.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/2/11
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket getDocket(){

        //创建封面信息对象
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("智慧医疗预约挂号服务平台-短信管理-后端接口说明")
                .description("此文档详细说明了智慧医疗预约挂号服务平台-短信管理-后端接口规范....")
                .version("v 2.0.1")
                .contact( new Contact("清风","www.qingfeng.com","qingfeng@java.com") );
        ApiInfo apiInfo =  apiInfoBuilder.build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo) //指定生成的文档中的封面信息：文档标题、版本、作者
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingfeng.service.msm.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

}
