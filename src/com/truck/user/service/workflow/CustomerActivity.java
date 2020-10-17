package com.truck.user.service.workflow;

import com.truck.user.Customer;
import com.truck.user.CustomerManager;
import com.truck.user.service.representation.CustomerRequest;
import com.truck.user.service.representation.CustomerRepresentation;

public class CustomerActivity {
	private static CustomerManager cm = new CustomerManager();
	
	public CustomerRepresentation createCustomer(CustomerRequest custReq) {
		Customer newCust = new Customer();
		newCust.setFirstName(custReq.getFirstName());
		newCust.setFirstName(custReq.getLastName());
		int id = cm.addCustomer(newCust);
		return getCustomer(id);
	}
	
	public CustomerRepresentation getCustomer(int customerId) {
		
		Customer cust = cm.findCustomerById(customerId);
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(cust.getFirstName());
		custRep.setLastName(cust.getLastName());
		custRep.setCustomerId(cust.getCustomerId());
		
		return custRep;
	}
	
	public CustomerRepresentation editCustomer(int id, CustomerRequest custReq) {
		Customer cust = new Customer();
		cust.setFirstName(custReq.getFirstName());
		cust.setFirstName(custReq.getLastName());
		cm.editCustomer(cust);
		return getCustomer(id);
	}
	
	public String deleteCustomer(int id) {
		
		//dao.deleteEmployee(id);
		cm.deleteCustomer(id);
		
		return "OK";
	}
	
}
