package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface CustomerService {
	public CustomerRepresentation getCustomer(int id);
	public CustomerRepresentation createCustomer(CustomerRequest CustomerRequest);
    public CustomerRepresentation updateCustomer(int id,CustomerRequest CustomerRequest);
    public Response deleteCustomer(int id);
}
