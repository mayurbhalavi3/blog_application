package com.example.blog.config;

import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ContentConfig  implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer.favorParameter(true)
		.parameterName("MediaType")
		.defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON)
		.mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON)
		.mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML);
		
	}

	
	
	
}
