package com.spring.template.configuration;

import static springfox.documentation.builders.PathSelectors.any;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "com.spring.template")
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(getSwaggerPaths())
        .build();
  }

  /**
   * API Info as it appears on the swagger-ui page
   */
  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
        "News API",
        "Mobile applications and beyond!",
        "0.0.1-SNAPSHOT",
        "https://helloreverb.com/terms/",
        "matt@raibledesigns.com",
        "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0.html"
    );
    return apiInfo;
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private Predicate<String> getSwaggerPaths() {
    return any();
//    return or(
//        regex("/api.*"),
//        regex("/test.*"));
  }
}