package com.truck.service.workflow;

import java.util.List;

import com.truck.domain.manager.VehicleManager;
import com.truck.domain.model.product.Vehicle;
import com.truck.domain.model.Link;
import com.truck.service.representation.AbstractRepresentation;
import com.truck.service.representation.ListRepresentation;
import com.truck.service.representation.VehicleRepresentation;
import com.truck.service.representation.VehicleRequest;

public class VehicleActivity{
	private static VehicleManager vm = new VehicleManager();
	
	public VehicleRepresentation getVehicle(int vehicleId,  int id, int cop) {
		Vehicle veh = vm.getVehicle(vehicleId);
		VehicleRepresentation vehRep = new VehicleRepresentation();
		vehRep.setVehicleId(veh.getProductId());
		vehRep.setPartnerId(veh.getPartnerId());
		vehRep.setPrice(veh.getPrice());
		vehRep.setMake(veh.getMake());
		vehRep.setModel(veh.getModel());
		vehRep.setYear(veh.getYear());
		vehRep.setAvailability(veh.getAvailability());
		vehRep.setOdometer(veh.getOdometer());
		vehicleLinks(vehRep, id, cop);
		return vehRep;
	}
	
	public ListRepresentation getInventory(int id, int cop){
		List<Vehicle> inv = vm.getSiteInventory();
		ListRepresentation invRep = new ListRepresentation();
		Vehicle veh;
		VehicleRepresentation vehRep = new VehicleRepresentation();

		Link view = new Link();
		
		for(int i = 0; i < inv.size(); i++) {
			veh = inv.get(i);
			vehRep = getVehicle(veh.getProductId(), id, cop);
			view = new Link("View Vehicle", "http://localhost:8081/VehicleService/vehicle/" + vehRep.getVehicleId() + "?id="+ id + "&cop=" + cop, "application/vnd.truck+xml");	
			vehRep.setLinks(view);
			invRep.setToList(vehRep);
		}
		return invRep;
	}
	
	// all below are for the use of partners and are only called through the PartnerResource
	public ListRepresentation getPartnerInventory(int partnerId, int id, int cop){
		List<Vehicle> inv = vm.getPartnerInventory(partnerId);
		ListRepresentation invRep = new ListRepresentation();
		Vehicle veh;
		VehicleRepresentation vehRep = new VehicleRepresentation();

		Link view = new Link();
		
		for(int i = 0; i < inv.size(); i++) {
			veh = inv.get(i);
			vehRep = getVehicle(veh.getProductId(), id, cop);
			view = new Link("View Vehicle", "http://localhost:8081/VehicleService/vehicle/" + vehRep.getVehicleId() + "?id="+ id + "&cop=" + cop, "application/vnd.truck+xml");	
			vehRep.setLinks(view);
			invRep.setToList(vehRep);
		}
		return invRep;
	}
	
	public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, int partnerId, int id, int cop) {
		Vehicle newVeh = new Vehicle();
		newVeh.setPrice(vehicleRequest.getPrice());
		newVeh.setPartnerId(id);
		newVeh.setMake(vehicleRequest.getMake());
		newVeh.setModel(vehicleRequest.getModel());
		newVeh.setYear(vehicleRequest.getYear());
		newVeh.setAvailability("Available");
		newVeh.setOdometer(vehicleRequest.getOdometer());
		int vehId = vm.addVehicle(newVeh);
		return getVehicle(vehId, id, cop);
	}
	
	public VehicleRepresentation editVehicle(VehicleRequest vehicleRequest, int productId, int partnerId, int id, int cop) {
		Vehicle veh = new Vehicle();
		veh.setProductId(productId);
		veh.setPrice(vehicleRequest.getPrice());
		veh.setMake(vehicleRequest.getMake());
		veh.setModel(vehicleRequest.getModel());
		veh.setYear(vehicleRequest.getYear());
		veh.setAvailability("Available");
		veh.setOdometer(vehicleRequest.getOdometer());
		System.out.print(veh.getMake()+ " " +veh.getModel()+ " "+veh.getYear()+ " "+ " "+veh.getPrice()+ " ");
		vm.editVehicle(veh, id);
		return getVehicle(productId, id, cop);
	}
	
	public String deleteVehicle(int partnerId, int productId) {
		vm.deleteVehicle(productId, partnerId);
		return "OK";
	}
	
	public void vehicleLinks(VehicleRepresentation vehRep, int id, int cop) {
		Link viewOwner = new Link("View Vehicle Owner", "http://localhost:8081/PartnerService/partner/" + vehRep.getPartnerId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		
		switch(cop) {
		case 1:// Customer
			Link rent = new Link("Rent", "http://localhost:8081/OrderService/order?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			vehRep.setLinks(viewOwner, rent);
			break;
		case 2:// Partner
			if(vehRep.getPartnerId() == id) {
				Link edit = new Link("Edit Vehicle", "http://localhost:8081/PartnerService/partner/" + id + "/inventory/" + vehRep.getVehicleId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link delete = new Link("Delete Vehicle", "http://localhost:8081/PartnerService/partner/" + id + "/inventory/" + vehRep.getVehicleId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				vehRep.setLinks(edit, delete);
			}else {
				vehRep.setLinks(viewOwner);
			}
			break;

		default:// Viewer
			vehRep.setLinks(viewOwner);
			break;
		}
	}
}
