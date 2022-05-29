package com.microservice.service;

import java.util.List;

import com.microservice.entity.Item;

public interface ItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	

}
