package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


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
}
