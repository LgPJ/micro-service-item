package com.microservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.entity.Item;
import com.microservice.entity.Product;

@Service
public class ItemService {

	
	//Dentro del item se inyecta el metodo registrado RestTemplate
	@Autowired
	private RestTemplate customerRestTemplate;
	
	public List<Item> findAll(){
		
		List<Product> product = Arrays.asList(customerRestTemplate.getForObject("http://localhost:7070/product/find", Product[].class));
		
		return product.stream()
				.map(prod -> new Item(prod, 1))
				.collect(Collectors.toList());
	}
	
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		
		Product product = customerRestTemplate.getForObject("http://localhost:7070/product/{id}", Product.class, pathVariable);
		
		return new Item(product, cantidad);
		
	}
}
