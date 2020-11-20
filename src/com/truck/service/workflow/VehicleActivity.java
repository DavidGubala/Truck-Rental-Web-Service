package com.truck.service.workflow;

import java.util.ArrayList;
import java.util.List;

import com.truck.domain.manager.VehicleManager;
import com.truck.domain.model.product.Vehicle;
import com.truck.domain.model.user.Partner;
import com.truck.domain.model.Link;
import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.representation.VehicleRequest;

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
			
			Link viewVehicle = new Link("view", 
					"http://localhost:8081/VehicleService/vehicle/?vehicleId=" + vehRep.getVehicleId(), "xml");	
			vehRep.setLinks(viewVehicle);
			
			invRep.add(vehRep);
		}
		return invRep;
	}

	// all below are for the use of partners and are only called through the PartnerResource
	public List<VehicleRepresentation> getPartnerInventory(int id){
		List<Vehicle> inv = vm.getPartnerInventory(id);
		List<VehicleRepresentation> invRep = new ArrayList<VehicleRepresentation>();
		Vehicle veh;
		VehicleRepresentation vehRep = new VehicleRepresentation();
		
		for(int i = 0; i < inv.size(); i++) {
			veh = inv.get(i);
			vehRep = getVehicleP(veh.getProductId(), id);
			invRep.add(vehRep);
		}
		return invRep;
	}
	
	public VehicleRepresentation getVehicleP(int vehicleId, int partnerId) {
		Vehicle veh = vm.getVehicle(vehicleId);
		VehicleRepresentation vehRep = new VehicleRepresentation();
		vehRep.setVehicleId(veh.getProductId());
		vehRep.setPrice(veh.getPricePerMile());
		vehRep.setMake(veh.getMake());
		vehRep.setModel(veh.getModel());
		vehRep.setYear(veh.getYear());
		vehRep.setAvailability(veh.getAvailability());
		vehRep.setOdometer(veh.getOdometer());
		
		partnerOptions(vehRep, partnerId);
		
		return vehRep;
	}
	
	public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, int partnerId) {
		Vehicle newVeh = new Vehicle();
		newVeh.setPricePerMile(vehicleRequest.getPrice());
		newVeh.setMake(vehicleRequest.getMake());
		newVeh.setModel(vehicleRequest.getModel());
		newVeh.setYear(vehicleRequest.getYear());
		newVeh.setAvailability(vehicleRequest.getAvailability());
		newVeh.setOdometer(vehicleRequest.getOdometer());
		int id = vm.addVehicle(newVeh, partnerId);
		return getVehicleP(id, partnerId);
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
		return getVehicleP(productId, partnerId);
	}
	
	public String deleteVehicle(int partnerId, int productId) {
		vm.deleteVehicle(productId, partnerId);
		return "OK";
	}
	
	public void partnerOptions(VehicleRepresentation vehRep, int partnerId) {
		Link self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/?partnerId=" + partnerId, "xml");
		Link edit = new Link("updateVehicle", "http://localhost:8081/PartnerService/partner/?partnerId=" + partnerId + "inventory/?productId=" + vehRep.getVehicleId() , "xml");
		Link delete = new Link("deleteProduct", "http://localhost:8081/PartnerService/partner/?partnerId=" + partnerId + "inventory/?productId=" + vehRep.getVehicleId() , "xml");
		
		vehRep.setLinks(self, edit, delete);
	}
	
	public void customerOptions(VehicleRepresentation vehRep, int customerId) {
		Link self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/?partnerId=" + customerId, "xml");
		Link rent = new Link("createOrder", "http://localhost:8081/OrderService/order?customer+vehicle", "xml"); // Needs Fixing
		
		vehRep.setLinks(self, rent);
	}

}
