package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface CustomerService{
	public Response loginCustomer(LoginRequest lr);
	public Response getCustomer(int customerId, int id, int cop);
	public Response createCustomer(CustomerRequest CustomerRequest);
	public Response getCustomerOrders(int id,int cop);
    public Response updateCustomer(int customerId, CustomerRequest CustomerRequest, int id, int cop);
    public Response deleteCustomer(int id);
}
