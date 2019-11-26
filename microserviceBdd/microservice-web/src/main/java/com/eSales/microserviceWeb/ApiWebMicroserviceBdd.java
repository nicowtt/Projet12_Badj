package com.eSales.microserviceWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eSales"})
@EnableJpaRepositories("com.eSales")
@EntityScan("com.eSales")
public class ApiWebMicroserviceBdd {

	public static void main(String[] args) {
		SpringApplication.run(ApiWebMicroserviceBdd.class, args);
	}

}
