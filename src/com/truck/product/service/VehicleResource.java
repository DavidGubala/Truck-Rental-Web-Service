package com.truck.product.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.truck.product.service.workflow.VehicleActivity;
import com.truck.product.service.representation.VehicleRepresentation;
import com.truck.product.service.representation.VehicleRequest;

@Path("/VehicleService/")
public class VehicleResource implements VehicleService {

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/vehicle")
	

}
