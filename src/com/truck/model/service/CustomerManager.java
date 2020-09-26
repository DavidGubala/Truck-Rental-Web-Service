package com.truck.model.service;

import com.truck.model.user.*;
import com.truck.dal.*;

public class CustomerManager {
	
	private CustomerDAO custDAO = new CustomerDAO();
	
	//Create
	public void addCustomer(Customer customer) {
		
		try {
			custDAO.addCustomer(customer);
	    } catch (Exception se) {
	      System.err.println("CustomerService: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
	}
	// Read
	public Customer findCustomerById(int customerId) {
		
		try {
			Customer customer = custDAO.getCustomer(customerId);
	    	return customer;
	    } catch (Exception se) {
	      System.err.println("CustomerService: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
}
