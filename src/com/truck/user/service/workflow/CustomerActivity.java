package com.truck.user.service.workflow;

import java.util.HashSet;
import java.util.Set;

import com.truck.user.Customer;
import com.truck.user.CustomerManager;
import com.truck.user.service.representation.CustomerRepresentation;

public class CustomerActivity {
	private static CustomerManager cm = new CustomerManager();
	
	public CustomerRepresentation getCustomer(int customerId) {
		
		Customer cust = cm.findCustomerById(customerId);
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(cust.getFirstName());
		custRep.setLastName(cust.getLastName());
		custRep.setCustomerId(cust.getCustomerId());
		
		return custRep;
	}
	
	public CustomerRepresentation createCustomer(Customer cust) {
		
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(cust.getFirstName());
		custRep.setLastName(cust.getLastName());
		custRep.setCustomerId(cust.getCustomerId());
		
		return custRep;
	}
	
	public String deleteEmployee(int id) {
		
		//dao.deleteEmployee(id);
		cm.deleteCustomer(id);
		
		return "OK";
	}
	
}
