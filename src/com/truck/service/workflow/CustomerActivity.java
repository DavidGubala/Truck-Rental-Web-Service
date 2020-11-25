package com.truck.service.workflow;

import com.truck.domain.manager.CustomerManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.user.Customer;
import com.truck.service.representation.CustomerRepresentation;
import com.truck.service.representation.CustomerRequest;

public class CustomerActivity{
	private static CustomerManager cm = new CustomerManager();
	
	public CustomerRepresentation createCustomer(CustomerRequest custReq) {
		Customer newCust = new Customer();
		newCust.setFirstName(custReq.getFirstName());
		newCust.setLastName(custReq.getLastName());
		int customerId = cm.addCustomer(newCust);
		return getCustomer(customerId, customerId, 1);
	}
	
	public CustomerRepresentation getCustomer(int customerId, int id, int cop) {
		
		Customer cust = cm.getCustomer(customerId);
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
		Link self = new Link();
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		System.out.print(cop);
		
		switch(cop) {
		case 1:// Customer
			System.out.print("adding customer links");
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			
			if(custRep.getCustomerid() == id) {
				Link edit = new Link("updateCustomer", "http://localhost:8081/CustomerService/customer/" + custRep.getCustomerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link delete = new Link("deleteCustomer", "http://localhost:8081/CustomerService/customer/" + custRep.getCustomerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");	
				Link viewOrders = new Link("getOrders",  "http://localhost:8081/CustomerService/customer/" + custRep.getCustomerid() + "/orders" + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				custRep.setLinks(self, viewSiteInventory, edit, delete, viewOrders);
			}else {
				custRep.setLinks(self, viewSiteInventory);
			}
			break;
		case 2:// Partner
			System.out.print("adding partner links");
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			custRep.setLinks(self, viewSiteInventory);
			break;
		default:// Viewer
			custRep.setLinks(viewSiteInventory);
			break;
		}
	}
}
