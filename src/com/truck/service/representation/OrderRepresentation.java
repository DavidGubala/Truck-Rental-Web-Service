package com.truck.service.representation;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation extends AbstractRepresentation{
	private int orderId;
	private int vehicleId;
	private int customerId;
	private int partnerId;
	private Date orderDate;
	
	public OrderRepresentation() {}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Date getDate() {
		return orderDate;
	}
	
	public void setDate(Date date) {
		this.orderDate = date;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(int id) {
		this.vehicleId = id;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int id) {
		this.customerId = id;
	}
	
	public int getPartnerId() {
		return partnerId;
	}
	
	public void setPartnerId(int id) {
		this.partnerId = id;
	}
}
