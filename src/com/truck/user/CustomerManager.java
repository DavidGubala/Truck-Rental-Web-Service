package com.truck.user;

import com.truck.dal.CustomerDAO;

public class CustomerManager {
	
	private CustomerDAO custDAO = new CustomerDAO();
	
	//Create
	public void addCustomer(Customer customer) {
		
		try {
			custDAO.addCustomer(customer);
	    } catch (Exception se) {
	      System.err.println("CustomerManager: Threw a Exception adding customer.");
	      System.err.println(se.getMessage());
	    }
	}
	// Read
	public Customer findCustomerById(int customerId) {
		
		try {
			Customer customer = custDAO.getCustomer(customerId);
	    	return customer;
	    } catch (Exception se) {
	      System.err.println("CustomerManager: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	//Update
	public void editCustomer(Customer cust) {
		try {
			custDAO.editCustomer(cust);
	    } catch (Exception se) {
	      System.err.println("CustomerManager: Threw a Exception editing customer.");
	      System.err.println(se.getMessage());
	    }
	}
	//Delete
	public void deleteCustomer(int customerId) {
		try {
			custDAO.deleteCustomer(customerId);
	    } catch (Exception se) {
	      System.err.println("CustomerManager: Threw a Exception deleting customer.");
	      System.err.println(se.getMessage());
	    }
	}
}
