package com.truck.service;

import javax.jws.WebService;

import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.VehicleRepresentation;

@WebService
public interface VehicleService {
		public VehicleRepresentation getVehicle(int vehicleId, int id, int cop);
		public ListRepresentation getSiteInventory(int id, int cop);
}
