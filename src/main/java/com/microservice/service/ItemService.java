package com.microservice.service;

import java.util.List;

import com.microservice.entity.Item;
import com.microservice.entity.Product;

public interface ItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
	public Product save(Product product);
	
	public Product update(Product product, Long id);
	
	public void delete(Long id);

}
