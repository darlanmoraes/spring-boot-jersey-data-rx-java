package com.boot.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by darlan on 25/01/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ResourceConfig jerseyConfig() {
		return new ResourceConfig()
			.register(PersonsImpl.class);
	}

}
