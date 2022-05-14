package com.microservice.entity;

public class Item {

	private Product product;
	private Integer cantidad;

	public Item() {
	}

	public Item(Product product, Integer cantidad) {
		this.product = product;
		this.cantidad = cantidad;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		
		return product.getQty() * cantidad.doubleValue();
	}

}
