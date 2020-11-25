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

import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.PartnerRepresentation;
import com.truck.service.representation.PartnerRequest;
import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.representation.VehicleRequest;
import com.truck.service.workflow.OrderActivity;
import com.truck.service.workflow.PartnerActivity;
import com.truck.service.workflow.VehicleActivity;

@Path("/PartnerService/")
public class PartnerResource implements PartnerService {

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/partner")
	public PartnerRepresentation createPartner(PartnerRequest partnerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName());
		PartnerActivity partActivity = new PartnerActivity();
		return partActivity.createPartner(partnerRequest);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client with PartnerID int ............." + id);
		PartnerActivity partActivity = new PartnerActivity();
		return partActivity.getPartner(partnerId, id, cop);
	}

	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public PartnerRepresentation updatePartner(@PathParam("partnerId") int partnerId, PartnerRequest partnerRequest, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("PUT METHOD Request from Client with ............." + id + "  " +partnerRequest.getFirstName() + "  " + partnerRequest.getLastName());
		PartnerActivity partActivity = new PartnerActivity();
		return partActivity.editPartner(partnerId, partnerRequest, id, cop);
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
	public ListRepresentation getPartnerInventory(@PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client Inventory with PartnerID int ............." + id);
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.getPartnerInventory(partnerId, id, cop);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/orders")
	public ListRepresentation getPartnerOrders(@PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client Orders with PartnerID int ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.getOrders(id, cop);
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/inventory")
	public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, @PathParam("partnerId") int partnerId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("POST METHOD Request from Client with ............." + vehicleRequest.getYear() + "  " + vehicleRequest.getMake()+ "  " + vehicleRequest.getModel());
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.addVehicle(vehicleRequest, partnerId, id, cop);
	}
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}/inventory/{productId}")
	public VehicleRepresentation updateVehicle(VehicleRequest vehicleRequest, @PathParam("partnerId") int partnerId, @PathParam("productId") int productId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("PUT METHOD Request from Client with ProductID int............." + productId);
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.editVehicle(vehicleRequest, productId, partnerId, id, cop);
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
