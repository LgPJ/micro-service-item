package com.microservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.microservice.customer.RestCustomerProduct;
import com.microservice.entity.Item;
import com.microservice.commons.entity.Product;

@Service("serviceFeign")
@Primary
public class ItemFeingService implements ItemService  {
	
	@Autowired
	private RestCustomerProduct restFeingCustomerProduct;
	
	public List<Item> findAll(){
		
		return restFeingCustomerProduct.findAll()
				.stream()
				.map(prod -> new Item(prod, 1))
				.collect(Collectors.toList());
		
	}
	
	public Item findById(Long id, Integer cantidad) {
		
		return new Item(restFeingCustomerProduct.findById(id), cantidad);
		
	}

	@Override
	public Product save(Product product) {
		
		return restFeingCustomerProduct.save(product);
	}

	@Override
	public Product update(Product product, Long id) {

		return restFeingCustomerProduct.update(product, id);
	}

	@Override
	public void delete(Long id) {
		restFeingCustomerProduct.delete(id);
		
	}

}
