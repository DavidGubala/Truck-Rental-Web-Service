package com.truck.product.service.workflow;

import com.truck.product.Vehicle;
import com.truck.product.VehicleManager;
import com.truck.product.service.representation.VehicleRequest;
import com.truck.product.service.representation.VehicleRepresentation;


public class VehicleActivity {
	private static VehicleManager vm = new VehicleManager();
	
	public VehicleRepresentation getVehicle(int vehicleId) {
		
		Vehicle veh = vm.findVehicleById(vehicleId);
		VehicleRepresentation vehRep = new VehicleRepresentation();
		vehRep.setVehicleId(veh.getVehicleId());
		vehRep.setMake(veh.getMake());
		vehRep.setModel(veh.getModel());
		vehRep.setOdometer(veh.getOdometer());
		vehRep.setPlateNumber(veh.getPlateNumber());
		vehRep.setType(veh.getType());
		vehRep.setVin(veh.getVin());
		vehRep.setYear(veh.getYear());
		
		return vehRep;
	}
	
	
}