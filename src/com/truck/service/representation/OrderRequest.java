package com.truck.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.truck.domain.model.product.Vehicle;
import com.truck.domain.model.user.Customer;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	private Customer customer;
	private Vehicle vehicle;
	
	public OrderRequest() {}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer cust) {
		this.customer = cust;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
