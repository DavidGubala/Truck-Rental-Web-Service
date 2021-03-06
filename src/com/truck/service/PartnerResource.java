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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.LoginRequest;
import com.truck.service.representation.PartnerRepresentation;
import com.truck.service.representation.PartnerRequest;
import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.representation.VehicleRequest;
import com.truck.service.workflow.OrderActivity;
import com.truck.service.workflow.PartnerActivity;
import com.truck.service.workflow.VehicleActivity;

@Path("/PartnerService/")
public class PartnerResource implements PartnerService {
	@OPTIONS
	@Path("/partner/login")
	@Produces({"application/xml" , "application/json"})
	public Response logres() {
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
	@Path("/partner/login")
	public Response loginPartner(LoginRequest lr) {
		System.out.println("GET METHOD Request from Client with PartnerID username ............." + lr.getUserName());
		PartnerActivity partActivity = new PartnerActivity();
		int id = partActivity.login(lr.getUserName(), lr.getPassword());
		ResponseBuilder rb;
		if(id == 0) {
			rb = Response.noContent();
		}else {
			rb = Response.ok().entity(partActivity.getPartner(id, id, 2)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		}
		Response response = rb.build();
		return response;
	}
	
	@OPTIONS
	@Path("/partner")
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
	@Path("/partner")
	public Response createPartner(PartnerRequest partnerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName());
		PartnerActivity partActivity = new PartnerActivity();
		ResponseBuilder rb = Response.ok().entity(partActivity.createPartner(partnerRequest)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public Response getPartner(@PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client with PartnerID int ............." + id);
		PartnerActivity partActivity = new PartnerActivity();
		ResponseBuilder rb = Response.ok().entity(partActivity.getPartner(partnerId, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@OPTIONS
	@Path("/partner/{partnerId}")
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
	@Path("/partner/{partnerId}")
	public Response updatePartner(@PathParam("partnerId") int partnerId, PartnerRequest partnerRequest, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("PUT METHOD Request from Client with ............." + id + "  " +partnerRequest.getFirstName() + "  " + partnerRequest.getLastName());
		PartnerActivity partActivity = new PartnerActivity();
		ResponseBuilder rb = Response.ok().entity(partActivity.editPartner(partnerId, partnerRequest, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public Response deletePartner(@PathParam("partnerId") int id) {
		System.out.println("DELETE METHOD Request from Client with PartnerID int ............." + id);
		PartnerActivity partActivity = new PartnerActivity();
		String res = partActivity.deletePartner(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/inventory")
	public Response getPartnerInventory(@PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client Inventory with PartnerID int ............." + id);
		VehicleActivity vehActivity = new VehicleActivity();
		ResponseBuilder rb = Response.ok().entity(vehActivity.getPartnerInventory(partnerId, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/orders")
	public Response getPartnerOrders(@PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client Orders with PartnerID int ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		ResponseBuilder rb = Response.ok().entity(orderActivity.getOrders(id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@OPTIONS
	@Path("/partner/{partnerId}/inventory")
	@Produces({"application/xml" , "application/json"})
	public Response vehaddres() {
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
	@Path("/partner/{partnerId}/inventory")
	public Response addVehicle(VehicleRequest vehicleRequest, @PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("POST METHOD Request from Client with ............." + vehicleRequest.getYear() + "  " + vehicleRequest.getMake()+ "  " + vehicleRequest.getModel());
		VehicleActivity vehActivity = new VehicleActivity();
		ResponseBuilder rb = Response.ok().entity(vehActivity.addVehicle(vehicleRequest, partnerId, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@OPTIONS
	@Path("/partner/{partnerId}/inventory/{productId}")
	@Produces({"application/xml" , "application/json"})
	public Response veheditres() {
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
	@Path("/partner/{partnerId}/inventory/{productId}")
	public Response updateVehicle(VehicleRequest vehicleRequest, @PathParam("partnerId") int partnerId, @PathParam("productId") int productId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("PUT METHOD Request from Client with ProductID int............." + productId);
		VehicleActivity vehActivity = new VehicleActivity();
		ResponseBuilder rb = Response.ok().entity(vehActivity.editVehicle(vehicleRequest, productId, partnerId, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/inventory/{productId}")
	public Response deleteProduct(@PathParam("partnerId") int partnerId, @PathParam("productId") int productId) {
		System.out.println("DELETE METHOD Request from Client with PartnerID,ProductID int ............." + partnerId + "," + productId);
		VehicleActivity vehActivity = new VehicleActivity();
		String res = vehActivity.deleteVehicle(partnerId,productId);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
}
