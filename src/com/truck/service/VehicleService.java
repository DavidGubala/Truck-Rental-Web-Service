package com.truck.service;

import java.util.List;

import javax.jws.WebService;

import com.truck.service.representation.VehicleRepresentation;

@WebService
public interface VehicleService {
		public VehicleRepresentation getVehicle(int id);
		public List<VehicleRepresentation> getSiteInventory();
}
