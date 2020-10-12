package com.truck.user;

import java.util.ArrayList;
import java.util.List;

import com.truck.order.Order;
import com.truck.product.Product;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Partner extends User {
	private int partnerId;
	private List<Product> inventory = new ArrayList<Product>();
	private List<Order> orders = new ArrayList<Order>();
	
	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int id) {
		this.partnerId = id;
	}
	
	public List<Product> getInventory() {
		return inventory;
	}
	
	public void setInventory(List<Product> inv) {
		this.inventory = inv;
	}
	
	public void addProduct(Product prod) {
		inventory.add(prod);
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrders(Order ord) {
		orders.add(ord);
	}
}
