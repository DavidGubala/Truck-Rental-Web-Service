package com.truck.service.workflow;

import com.truck.domain.manager.CustomerManager;
import com.truck.domain.model.user.Customer;
import com.truck.service.representation.CustomerRepresentation;
import com.truck.service.representation.CustomerRequest;

public class CustomerActivity {
	private static CustomerManager cm = new CustomerManager();
	
	public CustomerRepresentation createCustomer(CustomerRequest custReq) {
		Customer newCust = new Customer();
		newCust.setFirstName(custReq.getFirstName());
		newCust.setLastName(custReq.getLastName());
		int id = cm.addCustomer(newCust);
		return getCustomer(id);
	}
	
	public CustomerRepresentation getCustomer(int customerId) {
		
		Customer cust = cm.getCustomer(customerId);
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(cust.getFirstName());
		custRep.setLastName(cust.getLastName());
		custRep.setCustomerId(cust.getCustomerId());
		
		return custRep;
	}
	
	public CustomerRepresentation editCustomer(int id, CustomerRequest custReq) {
		Customer cust = new Customer();
		cust.setCustomerId(id);
		cust.setFirstName(custReq.getFirstName());
		cust.setLastName(custReq.getLastName());
		cm.editCustomer(cust);
		return getCustomer(id);
	}
	
	public String deleteCustomer(int id) {
		cm.deleteCustomer(id);
		return "OK";
	}
	
}
