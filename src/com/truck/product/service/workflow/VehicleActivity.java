package com.truck.product.service.workflow;

import java.util.ArrayList;
import java.util.List;

import com.truck.product.Vehicle;
import com.truck.product.VehicleManager;
import com.truck.product.service.representation.VehicleRepresentation;
import com.truck.product.service.representation.VehicleRequest;
import com.truck.user.Partner;

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
	
	public List<VehicleRepresentation> getInventory(){
		List<Vehicle> inv = vm.getSiteInventory();
		List<VehicleRepresentation> invRep = new ArrayList<VehicleRepresentation>();
		Vehicle veh;
		VehicleRepresentation vehRep = new VehicleRepresentation();
		
		for(int i = 0; i < inv.size(); i++) {
			veh = inv.get(i);
			vehRep.setVehicleId(veh.getProductId());
			vehRep.setPrice(veh.getPricePerMile());
			vehRep.setMake(veh.getMake());
			vehRep.setModel(veh.getModel());
			vehRep.setYear(veh.getYear());
			vehRep.setAvailability(veh.getAvailability());
			vehRep.setOdometer(veh.getOdometer());
			invRep.add(vehRep);
		}
		return invRep;
	}

	// all below are for the use of partners and are only called through the PartnerResource
	public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, int partnerId) {
		Vehicle newVeh = new Vehicle();
		newVeh.setPricePerMile(vehicleRequest.getPrice());
		newVeh.setMake(vehicleRequest.getMake());
		newVeh.setModel(vehicleRequest.getModel());
		newVeh.setYear(vehicleRequest.getYear());
		newVeh.setAvailability(vehicleRequest.getAvailability());
		newVeh.setOdometer(vehicleRequest.getOdometer());
		int id = vm.addVehicle(newVeh, partnerId);
		return getVehicle(id);
	}
	
	public VehicleRepresentation editVehicle(VehicleRequest vehicleRequest, int productId, int partnerId) {
		Vehicle veh = new Vehicle();
		veh.setProductId(productId);
		veh.setPricePerMile(vehicleRequest.getPrice());
		veh.setMake(vehicleRequest.getMake());
		veh.setModel(vehicleRequest.getModel());
		veh.setYear(vehicleRequest.getYear());
		veh.setAvailability(vehicleRequest.getAvailability());
		veh.setOdometer(vehicleRequest.getOdometer());
		vm.editVehicle(veh, partnerId);
		return getVehicle(productId);
	}
	
	public String deleteVehicle(int partnerId, int productId) {
		vm.deleteVehicle(productId, partnerId);
		return "OK";
	}



}
