package com.truck.model.service;

import com.truck.model.order.*;
import com.truck.dal.OrderDAO;


public class OrderManager {
	
	private OrderDAO orderDAO = new OrderDAO();

	// Create
	public void createOrder(Order order) {
		try {
			orderDAO.creteOrder(order);
	    } catch (Exception se) {
	      System.err.println("OrderManager: Threw a Exception creating order.");
	      System.err.println(se.getMessage());
	    }
	}

	// Read
	public Order findOrderById(int orderId) {
		
		try {
			Order order = orderDAO.getOrder(orderId);
	    	return order;
	    } catch (Exception se) {
	      System.err.println("OrderManager: Threw a Exception retrieving Order.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	public void deleteOrder(int orderId) {
		try {
			orderDAO.deleteOrder(orderId);;
	    } catch (Exception se) {
	      System.err.println("OrderManager: Threw a Exception deleting order.");
	      System.err.println(se.getMessage());
	    }
	}
	
}
