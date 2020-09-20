package com.truck.model.order;

import com.truck.model.product.Product;

public class OrderDetail {

	private Product product;
	private int quantity;

	public OrderDetail() {}
	
	public OrderDetail(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}