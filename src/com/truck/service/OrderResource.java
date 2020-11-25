package com.truck.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.truck.service.representation.OrderRepresentation;
import com.truck.service.representation.OrderRequest;
import com.truck.service.workflow.OrderActivity;

@Path("/OrderService/")
public class OrderResource implements OrderService{
	
	@POST
	@Produces({"application/xml", "application/json"})
	@Path("/order")
	public OrderRepresentation createOrder(OrderRequest ordReq, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("POST METHOD Request from Client with ID ............." + ordReq.getCustomerId());
		OrderActivity oderActivity = new OrderActivity();
		return oderActivity.createOrder(ordReq, ordReq.getCustomerId(), cop);
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Path("/order/{orderId}")
	public OrderRepresentation getOrder(@PathParam("orderId") int orderId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client with OrderId int ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.getOrder(orderId, id, cop);
	}
	
	@PUT
	@Produces({"application/xml", "application/json"})
	@Path("/order/{orderId}")
	public OrderRepresentation updateOrder(@PathParam("orderId")int orderId, @QueryParam("id") int id, OrderRequest ordReq) {
		System.out.println("PUT METHOD Request from Client with ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.editOrder(orderId, id, ordReq);
	}
	
	@DELETE
	@Produces({"application/xml", "application/json"})
	@Path("/order/{orderId}")
	public Response deleteOrder(@PathParam("orderId")int id) {
		System.out.println("DELETE METHOD Request from Client with OrderId int ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		String res = orderActivity.deleteOrder(id);
		if(res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

}
