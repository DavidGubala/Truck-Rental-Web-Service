package com.truck.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.truck.service.representation.CustomerRepresentation;
import com.truck.service.representation.CustomerRequest;
import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.LoginRequest;
import com.truck.service.workflow.CustomerActivity;
import com.truck.service.workflow.OrderActivity;
import com.truck.service.workflow.PartnerActivity;

@Path("/CustomerService/")
public class CustomerResource implements CustomerService {
	
	@OPTIONS
	@Path("/customer/login")
	@Produces({"application/xml" , "application/json"})
	public Response logres(){
	    return Response
	      .status(200)
	      .header("Access-Control-Allow-Origin", "http://localhost:8080")
	      .header("Access-Control-Allow-Credentials", "true")
	      .header("Access-Control-Allow-Headers",
	        "access-control-allow-origin, content-type, accept, authorization")
	      .header("Access-Control-Allow-Methods", 
	        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	      .entity("")
	      .build();
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/login")
	public Response loginCustomer(LoginRequest lr) {
		System.out.println("GET METHOD Request from Client with CustomerID username ............." + lr.getUserName());
		CustomerActivity custActivity = new CustomerActivity();
		int id = custActivity.login(lr.getUserName(), lr.getPassword());
		System.out.print("the customer ID is =" + id);
		ResponseBuilder rb;
		if(id == 0) {
			rb = Response.ok().header("Access-Control-Allow-Origin", "http://localhost:8080");
		}else {
			System.out.print("WE GOT THIS FAR");
			rb = Response.ok().entity(custActivity.getCustomer(id, id, 1)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		}
		Response response = rb.build();
		return response;
	}

	@OPTIONS
	@Path("/customer")
	@Produces({"application/xml" , "application/json"})
	public Response regres() {
	    return Response
	      .status(200)
	      .header("Access-Control-Allow-Origin", "http://localhost:8080")
	      .header("Access-Control-Allow-Credentials", "true")
	      .header("Access-Control-Allow-Headers",
	        "access-control-allow-origin, content-type, accept, authorization")
	      .header("Access-Control-Allow-Methods", 
	        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	      .entity("")
	      .build();
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customer")
	public Response createCustomer(CustomerRequest customerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity custActivity = new CustomerActivity();
		ResponseBuilder rb = Response.ok().entity(custActivity.createCustomer(customerRequest)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public Response getCustomer(@PathParam("customerId") int customerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client with CustomerID int ............." + id);
		CustomerActivity custActivity = new CustomerActivity();
		ResponseBuilder rb = Response.ok().entity(custActivity.getCustomer(customerId, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}/orders")
	public Response getCustomerOrders(@PathParam("customerId") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client Orders with CustomerID int ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		ResponseBuilder rb = Response.ok().entity(orderActivity.getOrders(id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@OPTIONS
	@Path("/customer/{customerId}")
	@Produces({"application/xml" , "application/json"})
	public Response editres() {
	    return Response
	      .status(200)
	      .header("Access-Control-Allow-Origin", "http://localhost:8080")
	      .header("Access-Control-Allow-Credentials", "true")
	      .header("Access-Control-Allow-Headers",
	        "access-control-allow-origin, content-type, accept, authorization")
	      .header("Access-Control-Allow-Methods", 
	        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	      .entity("")
	      .build();
	}
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public Response updateCustomer(@PathParam("customerId") int customerId, CustomerRequest customerRequest, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("PUT METHOD Request from Client with ............." + id + "  " +customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		CustomerActivity custActivity = new CustomerActivity();
		ResponseBuilder rb = Response.ok().entity(custActivity.editCustomer(customerId, customerRequest, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerId}")
	public Response deleteCustomer(@PathParam("customerId")int id) {
		System.out.println("DELETE METHOD Request from Client with CustomerID int ............." + id);
		CustomerActivity custActivity = new CustomerActivity();
		String res = custActivity.deleteCustomer(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
}
