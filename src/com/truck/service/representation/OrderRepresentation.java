package com.truck.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.truck.domain.model.product.Vehicle;
import com.truck.domain.model.user.Customer;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {
	private int orderId;
	private Vehicle vehicle;
	private Customer customer;
	
	public OrderRepresentation() {}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer cust) {
		this.customer = cust;
	}
}
