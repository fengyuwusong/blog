package cn.niriqiang.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by fengyuwusong on 2017/9/20 22:48.
 */

//通过@Configuration注解，让Spring来加载该类配置。
// 再通过@EnableSwagger2注解来启用Swagger2。
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.niriqiang.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Blog")
                .description("博客接口介绍")
                .termsOfServiceUrl("www.niriqiang.cn")
                .contact("风雨雾凇")
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
