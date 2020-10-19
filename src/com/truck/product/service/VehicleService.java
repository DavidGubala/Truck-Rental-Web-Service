package com.truck.product.service;

import java.util.List;

import javax.jws.WebService;

import com.truck.product.service.representation.VehicleRepresentation;

@WebService
public interface VehicleService {
		public VehicleRepresentation getVehicle(int id);
		public List<VehicleRepresentation> getSiteInventory();
}
