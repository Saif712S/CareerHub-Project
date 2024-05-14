package com.Candidature.Candidature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.Candidature.Candidature")
@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EntityScan("com.Candidature.Candidature.Entity")

public class CandidatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidatureApplication.class, args);
	}

}
