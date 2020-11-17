package com.truck.domain.manager;

import java.util.Random;

import com.truck.dal.OrderDAO;
import com.truck.domain.model.order.Order;

public class OrderManager {
	
	private OrderDAO orderDAO = new OrderDAO();

	// Create
	public int createOrder(Order order) {
		Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(10000);
		
		try {
			order.setOrderId(id);
			orderDAO.createOrder(order);
	    } catch (Exception se) {
	      System.err.println("OrderManager: Threw a Exception creating order.");
	      System.err.println(se.getMessage());
	    }
		
		return id;
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
	
	// Update
	public void editOrder(Order order) {
		try {
			orderDAO.editOrder(order);
		} catch(Exception se) {
			System.err.println("OrderManager: Threw a Exception editing order.");
			System.err.println(se.getMessage());
		}
	}
	
	//Delete
	public void deleteOrder(int orderId) {
		try {
			orderDAO.deleteOrder(orderId);
		} catch (Exception se) {
			System.err.println("OrderManager: Threw a Exception deleting order.");
			System.err.println(se.getMessage());
		}
	}
}
