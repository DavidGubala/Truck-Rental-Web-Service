package com.truck.product.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.product.service.representation.*;

@WebService
public interface VehicleService {
	
		public VehicleRepresentation getVehicle(int id);
	    public VehicleRepresentation updateVehicle(int id, VehicleRequest VehicleRequest);
	    public Response deleteVehicle(int id);
}
