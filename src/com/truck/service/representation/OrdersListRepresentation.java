package com.truck.service.representation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Inventory")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrdersListRepresentation {
	private List<OrderRepresentation> orders = new ArrayList<OrderRepresentation>();
	
	public List<OrderRepresentation> getInventory() {
		return orders;
	}
	
	public void setToOrders(OrderRepresentation ordRep) {
		this.orders.add(ordRep);
	}
}
