package com.truck.product.service.workflow;

import com.truck.product.Vehicle;
import com.truck.product.VehicleManager;
import com.truck.product.service.representation.VehicleRepresentation;

public class VehicleActivity {
	private static VehicleManager vm = new VehicleManager();
	
	public VehicleRepresentation getVehicle(int vehicleId) {
		
		Vehicle veh = vm.getVehicle(vehicleId);
		VehicleRepresentation vehRep = new VehicleRepresentation();
		vehRep.setVehicleId(veh.getProductId());
		vehRep.setPrice(veh.getPricePerMile());
		vehRep.setMake(veh.getMake());
		vehRep.setModel(veh.getModel());
		vehRep.setYear(veh.getYear());
		vehRep.setAvailability(veh.getAvailability());
		vehRep.setOdometer(veh.getOdometer());		
		return vehRep;
	}
}
