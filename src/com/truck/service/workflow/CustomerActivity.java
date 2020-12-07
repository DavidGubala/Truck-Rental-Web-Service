package com.truck.service.workflow;

import com.truck.domain.manager.CustomerManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.user.Customer;
import com.truck.domain.model.user.Partner;
import com.truck.service.representation.CustomerRepresentation;
import com.truck.service.representation.CustomerRequest;

public class CustomerActivity{
	private static CustomerManager cm = new CustomerManager();
	
	public CustomerRepresentation createCustomer(CustomerRequest custReq) {
		Customer newCust = new Customer();
		newCust.setFirstName(custReq.getFirstName());
		newCust.setLastName(custReq.getLastName());
		newCust.setUserName(custReq.getUserName());
		newCust.setPassword(custReq.getPassword());
		int customerId = cm.addCustomer(newCust);
		return getCustomer(customerId, customerId, 1);
	}

	public int login(String user, String pass) {
		Customer cust = cm.getCustomerbyUser(user);
		System.out.print(cust.getPassword() + "   " + pass);
		if(cust.getPassword().equals(pass)) {
			return cust.getCustomerId();
		}else {
			return 0;
		}
	}
	
	public CustomerRepresentation getCustomer(int customerId, int id, int cop) {
		
		Customer cust = cm.getCustomerbyID(customerId);
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(cust.getFirstName());
		custRep.setLastName(cust.getLastName());
		custRep.setCustomerId(cust.getCustomerId());
		customerLinks(custRep, id, cop);
		return custRep;
	}
	
	public CustomerRepresentation editCustomer(int customerId, CustomerRequest custReq, int id, int cop) {
		Customer cust = new Customer();
		cust.setCustomerId(customerId);
		cust.setFirstName(custReq.getFirstName());
		cust.setLastName(custReq.getLastName());
		cm.editCustomer(cust);
		return getCustomer(customerId, id, cop);
	}
	
	public String deleteCustomer(int id) {
		cm.deleteCustomer(id);
		return "OK";
	}
	
	public void customerLinks(CustomerRepresentation custRep, int id, int cop) {
		//Link self = new Link();
		//Link viewSiteInventory = new Link("Site Inventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		
		switch(cop) {
		case 1:// Customer
			//self = new Link("Account Settings", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			if(custRep.getCustomerid() == id) {
				Link edit = new Link("Edit Customer", "http://localhost:8081/CustomerService/customer/" + custRep.getCustomerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link delete = new Link("Delete Customer", "http://localhost:8081/CustomerService/customer/" + custRep.getCustomerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");	
				Link viewOrders = new Link("Get Orders",  "http://localhost:8081/CustomerService/customer/" + custRep.getCustomerid() + "/orders" + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				custRep.setLinks(edit, delete, viewOrders);
			}
			break;
			/*
		case 2:// Partner
			//System.out.print("adding partner links");
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			custRep.setLinks(self);
			break;
			
		default:// Viewer
			custRep.setLinks(viewSiteInventory);
			break;
			*/
		}
	}
}
