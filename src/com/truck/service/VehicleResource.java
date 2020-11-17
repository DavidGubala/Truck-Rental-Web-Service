package com.truck.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.workflow.VehicleActivity;

@Path("/VehicleService/")
public class VehicleResource implements VehicleService {
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vehicle/{vehicleId}")
	public VehicleRepresentation getVehicle(@PathParam("vehicleId") int id) {
		System.out.println("GET METHOD Request from Client with VehicleID int ............." + id);
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.getVehicle(id);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vehicle")
	public List<VehicleRepresentation> getSiteInventory() {
		System.out.println("GET METHOD Request from Client for Entire Inventory");
		VehicleActivity vehActivity = new VehicleActivity();
		return vehActivity.getInventory();
	}
}
