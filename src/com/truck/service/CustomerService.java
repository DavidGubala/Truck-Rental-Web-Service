package com.truck.service;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface CustomerService {
	public CustomerRepresentation getCustomer(int customerId, int id, int cop);
	public CustomerRepresentation createCustomer(CustomerRequest CustomerRequest);
	public ListRepresentation getCustomerOrders(int id,int cop);
    public CustomerRepresentation updateCustomer(int customerId, CustomerRequest CustomerRequest, int id, int cop);
    public Response deleteCustomer(int id);
}
