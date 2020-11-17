package com.truck.domain.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.truck.domain.model.order.Order;

@XmlRootElement
public class Customer extends User{
	private int customerId;
	private License driverLicense;
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public License getDriverLicense() {
		return driverLicense;
	}
	
	public void setDriverLicense(License driverLicense) {
		this.driverLicense = driverLicense;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int id) {
		this.customerId = id;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
}
