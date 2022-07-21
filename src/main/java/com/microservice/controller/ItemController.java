package com.microservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.Item;
import com.microservice.entity.Product;
import com.microservice.service.ItemServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
@RequestMapping("/item")
public class ItemController {
	
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired(required = true)
	/*@Qualifier("ItemFeingService")*/
	@Qualifier("serviceRestTemplate")//Calificador para identificar el componente a utilizar
	private ItemServiceImpl itemService;
	
	@Value("${configuracion.texto}")
	private String text;
	
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
	

	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto){
		
		log.info(text);
		
		Map<String, String> json = new HashMap<>();
		json.put("text", text);
		json.put("puerto", puerto);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Product save(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		
		return itemService.update(product, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}
	
}
