package com.truck.model.customer;

import java.util.ArrayList;
import java.util.List;

import com.truck.model.customer.Address;
import com.truck.model.order.Order;


public class Customer {
	private String customerId;
	private String lastName;
	private String firstName;
	private String driverLicense;
	private Address billingAddress;
	private Address homeAddress;
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public String getDriverLicense() {
		return driverLicense;
	}
	
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String id) {
		this.customerId = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
}
