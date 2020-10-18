package com.truck.product.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.truck.product.service.workflow.VehicleActivity;
import com.truck.product.service.representation.VehicleRepresentation;

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
}
