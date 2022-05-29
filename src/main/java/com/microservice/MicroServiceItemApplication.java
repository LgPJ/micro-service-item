package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name = "product")
@EnableFeignClients
@SpringBootApplication
public class MicroServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceItemApplication.class, args);
	}

}
