package com.truck.service.workflow;

import java.util.List;

import com.truck.domain.manager.CustomerManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.user.Customer;
import com.truck.service.representation.CustomerRepresentation;
import com.truck.service.representation.CustomerRequest;
import com.truck.service.representation.PartnerRepresentation;

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
	
	public void customerLinks(CustomerRepresentation custRep) {
		Link self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/?customerId=" + custRep.getCustomerid(), "xml");
		Link edit = new Link("updateCustomer", "http://localhost:8081/PartnerService/partner/?partnerId=" + custRep.getCustomerid(), "xml");
		Link delete = new Link("deleteCustomer", "http://localhost:8081/PartnerService/partner/?partnerId=" + custRep.getCustomerid(), "xml");	
		Link viewOrders = new Link("getCustomerOrders",  "http://localhost:8081/PartnerService/partner/?partnerId=" + custRep.getCustomerid() + "/orders", "xml");
		Link viewInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?customerId=" + custRep.getCustomerid(), "xml");
		custRep.setLinks(self, edit, delete, viewOrders, viewInventory);
	}
	
}
