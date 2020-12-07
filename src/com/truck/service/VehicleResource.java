package com.truck.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.workflow.VehicleActivity;

@Path("/VehicleService/")
public class VehicleResource implements VehicleService {
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vehicle/{vehicleId}")
	public Response getVehicle(@PathParam("vehicleId") int vehicleId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client with VehicleID int ............." + id);
		VehicleActivity vehActivity = new VehicleActivity();
		ResponseBuilder rb = Response.ok().entity(vehActivity.getVehicle(vehicleId, id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
	
	@GET
	@Produces({"application/xml" ,"application/json"})
	@Path("/vehicle")
	public Response getSiteInventory(@QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client for Entire Inventory");
		VehicleActivity vehActivity = new VehicleActivity();
		ResponseBuilder rb = Response.ok().entity(vehActivity.getInventory(id, cop)).header("Access-Control-Allow-Origin", "http://localhost:8080");
		Response response = rb.build();
		return response;
	}
}
