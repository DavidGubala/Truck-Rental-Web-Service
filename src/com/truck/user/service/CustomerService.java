package com.truck.user.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.user.service.representation.*;

@WebService
public interface CustomerService {
	
		public CustomerRepresentation getCustomer(String CustomerId);
		public CustomerRepresentation createCustomer(CustomerRequest CustomerRequest);

	    public Response updateCustomer(CustomerRequest CustomerRequest);
	    public Response deleteCustomer(String CustomerId);
}
