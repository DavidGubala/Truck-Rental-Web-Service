package com.truck.user;

import java.util.ArrayList;
import java.util.List;

import com.truck.order.Order;
import com.truck.product.Product;

public class Partner extends User {
	private int partnerId;
	private List<Product> products = new ArrayList<Product>();
	
	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int id) {
		this.partnerId = id;
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
}
