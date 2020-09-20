package com.truck.model.order;

import com.truck.model.product.Product;

public class OrderDetail {

	private Product product;


	public OrderDetail() {}
	
	public OrderDetail(Product product) {
		this.product = product;
	
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	

}
