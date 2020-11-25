package com.truck.service.workflow;

import java.util.ArrayList;
import java.util.List;

import com.truck.domain.manager.OrderManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.order.Order;
import com.truck.service.representation.AbstractRepresentation;
import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.OrderRepresentation;
import com.truck.service.representation.OrderRequest;

public class OrderActivity{
	private static OrderManager om = new OrderManager();
	
	public OrderRepresentation createOrder(OrderRequest ordReq, int id, int cop) {
		Order newOrd = new Order();
		newOrd.setCustomerId(ordReq.getCustomerId());
		newOrd.setVehicleId(ordReq.getVehicleId());
		newOrd.setPartnerId(ordReq.getPartnerId());
		int orderId = om.createOrder(newOrd);
		return getOrder(orderId, id, cop);
	}
	
	public OrderRepresentation getOrder(int orderId, int id, int cop) {
		Order order = om.findOrderById(orderId);
		OrderRepresentation ordRep = new OrderRepresentation();
		ordRep.setVehicleId(order.getVehicleId());
		ordRep.setCustomerId(order.getCustomerId());
		ordRep.setPartnerId(order.getPartnerId());
		ordRep.setOrderId(order.getOrderId());
		orderLinks(ordRep, id, cop);
		return ordRep;
	}
	
	public ListRepresentation getOrders(int id, int cop){
		List<Order> orders = new ArrayList<Order>();
		ListRepresentation ordersRep =  new ListRepresentation();
		Order ord;
		OrderRepresentation ordRep =  new OrderRepresentation();
		AbstractRepresentation ab = new AbstractRepresentation();
		
		Link self = new Link();
		Link view = new Link();
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		
		switch(cop) {
		case 1:
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			orders = om.getCustomerOrders(id);
			System.out.print(orders);
			break;
		case 2:
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			orders = om.getPartnerOrders(id);
			break;
		}
		
		ab.setLinks(self, viewSiteInventory);
		ordersRep.setToList(ab);
		
		for(int i = 0; i < orders.size(); i++) {
			ord = orders.get(i);
			ordRep = getOrder(ord.getOrderId(), id, cop);
			
			view = new Link("getOrder",  "http://localhost:8081/OrderService/order/" + ordRep.getOrderId( ) + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			ordRep.setLinks(view);
			ordersRep.setToList(ordRep);
		}
		return ordersRep;
	}
	
	public OrderRepresentation editOrder(int orderId, int id, OrderRequest ordReq) {
		Order order = new Order();
		order.setOrderId(id);
		order.setCustomerId(ordReq.getCustomerId());
		order.setVehicleId(ordReq.getVehicleId());
		om.editOrder(order);
		return getOrder(orderId, id, 0);
	}
	
	public String deleteOrder(int id) {
		om.deleteOrder(id);
		return "OK";
	}
	
	public void orderLinks(OrderRepresentation ordRep, int id, int cop) {
		Link vehicle, customer, partner, self = new Link();
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		
		switch(cop) {
		case 1: // Customer
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			partner = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + ordRep.getPartnerId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			if(ordRep.getCustomerId() == id) {
				vehicle = new Link("getVehicle", "http://localhost:8081/VehicleService/vehicle/" + ordRep.getVehicleId() + "?id="+ id + "cop=" + cop, "application/vnd.truck+xml");
				ordRep.setLinks(self, vehicle, partner, viewSiteInventory);
			}else {
				ordRep.setLinks(self, viewSiteInventory);
			}
			break;
		case 2: // Partner
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			customer = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + ordRep.getPartnerId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			if(ordRep.getCustomerId() == id) {
				vehicle = new Link("getVehicle", "http://localhost:8081/VehicleService/vehicle/" + ordRep.getVehicleId() +  "?id="+ id + "?cop=" + cop, "application/vnd.truck+xml");
				customer = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + ordRep.getCustomerId() +  "?id="+ id + "?cop=" + cop, "application/vnd.truck+xml");
				ordRep.setLinks(self, viewSiteInventory, vehicle, customer);
			}else {
				ordRep.setLinks(self,viewSiteInventory);
			}
			break;
		default:// Viewer
			ordRep.setLinks(viewSiteInventory);
			break;
		}
	}
}
