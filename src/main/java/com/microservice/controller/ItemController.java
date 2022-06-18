package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.Item;
import com.microservice.entity.Product;
import com.microservice.service.ItemServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired(required = true)
	@Qualifier("serviceRestTemplate")//Calificador para identificar el componente a utilizar
	private ItemServiceImpl itemService;
	
	@GetMapping("/findItem")
	public List<Item> findAll(){
		return itemService.findAll();
		
	}
	
	//Se indica el metodo que puede dar cortocircuito y dentro se define el metodo alternativo
	//al cual va a recurrir si falla el mismo
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/id/{id}/cantidad/{cantidad}")
	public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	//El metodo alternativo debe ir de la misma forma del principal, mismos atributos
	//puede ser o no la misma logica de negocios o una logica alternativa
	public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		Item item = new Item();
		Product product = new Product();
		
		item.setCantidad(cantidad);
		product.setId(id);
		product.setName("JAJAJAJJA");
		product.setQty(50.0);
		item.setProduct(product);
		return item;
		
		//return itemService.findById(id, cantidad);
	}
	

}
