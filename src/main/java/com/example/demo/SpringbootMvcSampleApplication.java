package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SpringbootMvcSampleApplication extends SpringBootServletInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringbootMvcSampleApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootMvcSampleApplication.class);
	}

	public static void main(String[] args) {
		logger.info("### SpringbootMvcSampleApplication Start ###");
		SpringApplication.run(SpringbootMvcSampleApplication.class, args);
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	@Bean 
	@Order(0) 
	public MultipartFilter multipartFilter() { 
		MultipartFilter multipartFilter = new MultipartFilter(); 
		multipartFilter.setMultipartResolverBeanName("multipartReso‌​lver"); 
		return multipartFilter; 
	}
}
