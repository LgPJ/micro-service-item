package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.Item;
import com.microservice.service.ItemServiceImpl;

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
	
	@GetMapping("/id/{id}/cantidad/{cantidad}")
	public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	

}
