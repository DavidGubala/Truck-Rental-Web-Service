package com.truck.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	private int customerId;
	private int vehicleId;
	private int partnerId;
	
	public OrderRequest() {}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int id) {
		this.customerId = id;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(int id) {
		this.vehicleId = id;
	}
	
	public int getPartnerId() {
		return partnerId;
	}
	
	public void setPartnerId(int id) {
		this.partnerId = id;
	}
}
