package com.microservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	
	/** 
	 * Esta clase se genera para configurar el cliente restTemplate
	 * para que asi quede registrado dentro del contenedor
	 * 
	 **/
	
	/** Por defecto el metodo crea registrado con el nombre del metodo
	 * pero colocando dentro de la notacion BEAN un string ese seria el nuevo nombre del metodo registrado **/
	@Bean("customerRestTemplate")
	public RestTemplate registerRestTemplate() {
		
		return new RestTemplate();
	}
}
