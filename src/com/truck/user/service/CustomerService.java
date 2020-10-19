package com.truck.user.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.user.service.representation.*;

@WebService
public interface CustomerService {
	public CustomerRepresentation getCustomer(int id);
	public CustomerRepresentation createCustomer(CustomerRequest CustomerRequest);
    public CustomerRepresentation updateCustomer(int id,CustomerRequest CustomerRequest);
    public Response deleteCustomer(int id);
}
