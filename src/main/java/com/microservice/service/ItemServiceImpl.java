package com.microservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.entity.Item;
import com.microservice.entity.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	// Dentro del item se inyecta el metodo registrado RestTemplate
	@Autowired
	private RestTemplate customerRestTemplate;

	public List<Item> findAll() {

		/**
		 * http://localhost:7070/product/find se cambia el localHost y puerto, por el
		 * nombre del servicio que va a consumir dicho nombre esta ubicado en el
		 * properties del servicio que produce
		 */
		List<Product> product = Arrays
				.asList(customerRestTemplate.getForObject("http://product/product/find", Product[].class));
		return product.stream().map(prod -> new Item(prod, 1)).collect(Collectors.toList());
	}

	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());

		/**
		 * http://localhost:7070/product/{id} se cambia el localHost y puerto, por el
		 * nombre del servicio que va a consumir dicho nombre esta ubicado en el
		 * properties del servicio que produce
		 */
		Product product = customerRestTemplate.getForObject("http://product/product/{id}", Product.class, pathVariable);

		return new Item(product, cantidad);

	}

	@Override
	public Product save(Product product) {

		HttpEntity<Product> body = new HttpEntity<Product>(product);

		ResponseEntity<Product> response = customerRestTemplate.exchange("http://product/product/save", HttpMethod.POST,
				body, Product.class);

		return response.getBody();
	}

	@Override
	public Product update(Product product, Long id) {

		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());

		HttpEntity<Product> body = new HttpEntity<Product>(product);
		
		ResponseEntity<Product> response = customerRestTemplate.exchange("http://product/product/edit/{id}",
				HttpMethod.PUT, body, Product.class, pathVariable);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		
		customerRestTemplate.delete("http://product/product/delete/{id}", pathVariable);

	}
}
