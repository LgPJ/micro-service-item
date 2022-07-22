package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker //Notacion para activar Hystrix dentro del proyecto
@EnableEurekaClient //Notacion para definir el microservicio como cliente eureka
//@RibbonClient(name = "product") al anexar el EurekaClient se desacopla ribbon, ya que viene dentro de eureka
@EnableFeignClients
@SpringBootApplication
@EntityScan({"com.microservice.commons.entity"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MicroServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceItemApplication.class, args);
	}

}
