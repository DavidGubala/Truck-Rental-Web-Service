package com.truck.user.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.truck.user.service.workflow.CustomerActivity;
import com.truck.user.service.representation.CustomerRepresentation;
import com.truck.user.service.representation.CustomerRequest;

@Path("/CustomerService/")
public class CustomerResource implements CustomerService {

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customer")
	public CustomerRepresentation createCustomer(CustomerRequest customerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity custActivity = new CustomerActivity();
		return custActivity.createCustomer(customerRequest);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public CustomerRepresentation getCustomer(@PathParam("customerId") int id) {
		System.out.println("GET METHOD Request from Client with CustomerID int ............." + id);
		CustomerActivity custActivity = new CustomerActivity();
		return custActivity.getCustomer(id);
	}

	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public CustomerRepresentation updateCustomer(@PathParam("customerId") int id, CustomerRequest customerRequest) {
		System.out.println("PUT METHOD Request from Client with ............." + id + "  " +customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity custActivity = new CustomerActivity();
		return custActivity.editCustomer(id, customerRequest);
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public Response deleteCustomer(int id) {
		System.out.println("DELETE METHOD Request from Client with CustomerID int ............." + id);
		CustomerActivity custActivity = new CustomerActivity();
		String res = custActivity.deleteCustomer(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
}
