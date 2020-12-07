package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.VehicleRepresentation;

@WebService
public interface VehicleService {
		public Response getVehicle(int vehicleId, int id, int cop);
		public Response getSiteInventory(int id, int cop);
}
