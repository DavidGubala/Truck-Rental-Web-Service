package com.truck.model.order;

import com.truck.model.item.Product;

public class Order {
	private Product product;
	private String date;        //When did they order
	private String duration;    //How long they will rent
	//private int quantity;

	public Order() {}
	
//	public OrderDetail(Product product, int quantity) {
//		this.product = product;
//		this.quantity = quantity;
//	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
//	public int getQuantity() {
//		return quantity;
//	}
//	
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}

}
