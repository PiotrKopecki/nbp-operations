package com.nbp.operations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NbpOperationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbpOperationsServiceApplication.class, args);
	}

}
