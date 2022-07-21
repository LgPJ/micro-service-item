package com.microservice.customer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.entity.Product;

//Indicamos que es un cliente Feing, el nombre del microservicio a conectar y la uri del mismo
@FeignClient(name = "product")
public interface RestCustomerProduct {
	
	
	/** La notacion GetMapping, En el controlador se usa para mapear las rutas en las url
	 * mientras que en el cliente se usa para indicar la ruta de
	 * donde va a consumir el servicio
	 * */
	@GetMapping("/find")
	public List<Product> findAll();
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id);
	
	@PostMapping("/save")
	public Product save(@RequestBody Product product);
	
	@PutMapping("/edit/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);
}
