package com.truck.order.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.truck.order.service.workflow.OrderActivity;
import com.truck.order.service.representation.OrderRepresentation;
import com.truck.order.service.representation.OrderRequest;

@Path("/OrderService/")
public class OrderResource implements OrderService{
	
	@POST
	@Produces({"application/xml", "application/json"})
	@Path("/order")
	public OrderRepresentation createOrder(OrderRequest ordReq) {
		System.out.println("POST METHOD Request from Client with ............." + ordReq.getCustomer().getFirstName() + " " + ordReq.getCustomer().getLastName());
		OrderActivity oderActivity = new OrderActivity();
		return oderActivity.createOrder(ordReq);
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Path("/order/{orderId}")
	public OrderRepresentation getOrderId(@PathParam("orderId") int id) {
		System.out.println("GET METHOD Request from Client with OrderId int ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.getOrder(id);
	}
	
	@PUT
	@Produces({"application/xml", "application/json"})
	@Path("/order/{orderId}")
	public OrderRepresentation updateOrder(@PathParam("orderId")int id, OrderRequest ordReq) {
		System.out.println("PUT METHOD Request from Client with ............." + id + " " + ordReq.getCustomer().getFirstName() + " " + ordReq.getCustomer().getLastName());
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.editOrder(id, ordReq);
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