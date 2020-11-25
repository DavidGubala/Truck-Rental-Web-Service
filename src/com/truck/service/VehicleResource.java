package com.truck.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.workflow.VehicleActivity;

@Path("/VehicleService/")
public class VehicleResource implements VehicleService {
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vehicle/{vehicleId}")
	public VehicleRepresentation getVehicle(@PathParam("vehicleId") int vehicleId, @QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client with VehicleID int ............." + id);
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.getVehicle(vehicleId, id, cop);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vehicle")
	public ListRepresentation getSiteInventory(@QueryParam("id") int id, @QueryParam("cop") int cop) {
		System.out.println("GET METHOD Request from Client for Entire Inventory");
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.getInventory(id, cop);
	}
}