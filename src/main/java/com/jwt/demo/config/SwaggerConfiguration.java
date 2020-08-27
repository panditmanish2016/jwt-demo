package com.jwt.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket serviceApi() {
		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext())).select()
				.apis(RequestHandlerSelectors.basePackage("com.jwt.demo.controller")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiKey securityScheme() {
		return new ApiKey("Access Token", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(securityReferences()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> securityReferences() {
		return Arrays.asList(new SecurityReference("Access Token",
				new AuthorizationScope[] { new AuthorizationScope("Global", "Global Service Access") }));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Jwt Demo").description("Jwt Token Demo Application").version("1.0.0")
				.contact(new Contact("Jwt Demo", "#", null)).build();
	}
}
