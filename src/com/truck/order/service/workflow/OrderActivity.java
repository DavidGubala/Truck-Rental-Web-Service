package com.truck.order.service.workflow;

import com.truck.order.Order;
import com.truck.order.OrderManager;
import com.truck.order.service.representation.OrderRequest;
import com.truck.order.service.representation.OrderRepresentation;

public class OrderActivity {
	private static OrderManager om = new OrderManager();
	
	public OrderRepresentation createOrder(OrderRequest ordReq) {
		Order newOrd = new Order();
		newOrd.setCostumer(ordReq.getCustomer());
		newOrd.setVehicle(ordReq.getVehicle());
		int id = om.createOrder(newOrd);
		return getOrder(id);
	}
	
	public OrderRepresentation getOrder(int orderId) {
		Order order = om.findOrderById(orderId);
		OrderRepresentation ordRep = new OrderRepresentation();
		ordRep.setVehicle(order.getVehicle());
		ordRep.setCustomer(order.getCustomer());
		ordRep.setOrderId(order.getOrderId());
		
		return ordRep;
	}
	
	public OrderRepresentation editOrder(int id, OrderRequest ordReq) {
		Order order = new Order();
		order.setOrderId(id);
		order.setCostumer(ordReq.getCustomer());
		order.setVehicle(ordReq.getVehicle());
		om.editOrder(order);
		return getOrder(id);
	}
	
	public String deleteOrder(int id) {
		om.deleteOrder(id);
		
		return "OK";
	}
}
