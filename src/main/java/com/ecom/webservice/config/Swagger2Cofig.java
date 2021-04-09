package com.ecom.webservice.config;

import org.dom4j.tree.AbstractProcessingInstruction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Cofig {

	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ecom.webservice.controller"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo(){
		
		ApiInfo apiInfo = new ApiInfo("Product Service Api Documentation",
				"This is a productREST full webservice for Product CRUD Management", 
				"1.0.0", "Term of service as per user guide lines.", 
				new Contact("John Smith", "http://john.ecom/about", "john.smith@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0.html");
				return apiInfo;
	}
}
