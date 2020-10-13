package com.truck.user.service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.truck.user.service.representation.CustomerRepresentation;
import com.truck.user.service.representation.CustomerRequest;

@Path("/customerService/")
public class CustomerResource implements CustomerService {

	@Override
	public CustomerRepresentation getCustomer(String CustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerRepresentation createCustomer(CustomerRequest CustomerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateCustomer(CustomerRequest CustomerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteCustomer(String CustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
