package com.truck.product.service;

import javax.jws.WebService;

import com.truck.product.service.representation.VehicleRepresentation;

@WebService
public interface VehicleService {
		public VehicleRepresentation getVehicle(int id);
}
