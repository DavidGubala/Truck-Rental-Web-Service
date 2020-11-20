package com.truck.service.workflow;

import java.util.ArrayList;
import java.util.List;

import com.truck.domain.manager.OrderManager;
import com.truck.domain.model.order.Order;
import com.truck.service.representation.OrderRepresentation;
import com.truck.service.representation.OrderRequest;

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
		//ordRep.setVehicle(order.getVehicle());
		//ordRep.setCustomer(order.getCustomer());
		ordRep.setOrderId(order.getOrderId());
		
		return ordRep;
	}
	
	public List<OrderRepresentation> getOrdersP(int id){
		List<Order> orders = om.getPartnerOrders(id);
		List<OrderRepresentation> ordersRep =  new ArrayList<OrderRepresentation>();
		Order ord;
		OrderRepresentation ordRep =  new OrderRepresentation();
		
		for(int i = 0; i < orders.size(); i++) {
			ord = orders.get(i);
			ordRep.setOrderId(ord.getOrderId());
			ordRep.setVehicleId(ord.getVehicle().getProductId());
			ordRep.setCustomerId(ord.getCustomer().getCustomerId());
			ordRep.setDate(ord.getDate());
			ordersRep.add(ordRep);
		}
		return ordersRep;
	}
	
	public List<OrderRepresentation> getOrdersC(int id){
		List<Order> orders = om.getPartnerOrders(id);
		List<OrderRepresentation> ordersRep =  new ArrayList<OrderRepresentation>();
		Order ord;
		OrderRepresentation ordRep =  new OrderRepresentation();
		
		for(int i = 0; i < orders.size(); i++) {
			ord = orders.get(i);
			ordRep.setOrderId(ord.getOrderId());
			ordRep.setVehicleId(ord.getVehicle().getProductId());
			ordRep.setCustomerId(ord.getCustomer().getCustomerId());
			ordRep.setDate(ord.getDate());
			ordersRep.add(ordRep);
		}
		return ordersRep;
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
