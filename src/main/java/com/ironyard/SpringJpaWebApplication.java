package com.ironyard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class SpringJpaWebApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringJpaWebApplication.class, args);
	}

	@Bean
	public Docket chatApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("number-api")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/rest/call/*.*"))
				.build();
				/*
				.globalOperationParameters(
						newArrayList(new ParameterBuilder()
								.name("x-authorization-key")
								.description("API Authorization Key")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(true)
								.build()));*/

	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Number API")
				.description("Say Number")
				.termsOfServiceUrl("http://www.theironyard.com")
				.contact("Wail Yousif")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
				.version("2.1")
				.build();
	}
}
