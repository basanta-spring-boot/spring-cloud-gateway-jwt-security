package com.javatechie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Marker
public class EcomServiceResgistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomServiceResgistryApplication.class, args);
	}

}
