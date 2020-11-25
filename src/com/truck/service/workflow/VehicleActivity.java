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
		vehRep.setPrice(veh.getPricePerMile());
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
		AbstractRepresentation ab = new AbstractRepresentation();
		
		Link self = new Link();
		Link view = new Link();
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		
		switch(cop) {
		case 1:
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			break;
		case 2:
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			break;
		}
		
		ab.setLinks(self, viewSiteInventory);
		invRep.setToList(ab);
		
		for(int i = 0; i < inv.size(); i++) {
			veh = inv.get(i);
			vehRep = getVehicle(veh.getProductId(), id, cop);
			/*
			vehRep.setVehicleId(veh.getProductId());
			vehRep.setPrice(veh.getPricePerMile());
			vehRep.setMake(veh.getMake());
			vehRep.setModel(veh.getModel());
			vehRep.setYear(veh.getYear());
			vehRep.setAvailability(veh.getAvailability());
			vehRep.setOdometer(veh.getOdometer());
			*/
			view = new Link("getVehicle", "http://localhost:8081/VehicleService/vehicle/" + vehRep.getVehicleId() + "?id="+ id + "&cop=" + cop, "application/vnd.truck+xml");	
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
		AbstractRepresentation ab = new AbstractRepresentation();
		
		Link self = new Link();
		Link view = new Link();
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		
		switch(cop) {
		case 1:
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			break;
		case 2:
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			break;
		}
		
		ab.setLinks(self, viewSiteInventory);
		invRep.setToList(ab);
		
		for(int i = 0; i < inv.size(); i++) {
			veh = inv.get(i);
			vehRep = getVehicle(veh.getProductId(), id, cop);
			
			view = new Link("getVehicle", "http://localhost:8081/VehicleService/vehicle/" + vehRep.getVehicleId() + "?id="+ id + "&cop=" + cop, "application/vnd.truck+xml");	
			vehRep.setLinks(view);
			invRep.setToList(vehRep);
		}
		return invRep;
	}
	
	public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, int partnerId, int id, int cop) {
		Vehicle newVeh = new Vehicle();
		newVeh.setPricePerMile(vehicleRequest.getPrice());
		newVeh.setPartnerId(id);
		newVeh.setMake(vehicleRequest.getMake());
		newVeh.setModel(vehicleRequest.getModel());
		newVeh.setYear(vehicleRequest.getYear());
		newVeh.setAvailability(vehicleRequest.getAvailability());
		newVeh.setOdometer(vehicleRequest.getOdometer());
		int vehId = vm.addVehicle(newVeh);
		return getVehicle(vehId, id, cop);
	}
	
	public VehicleRepresentation editVehicle(VehicleRequest vehicleRequest, int productId, int partnerId, int id, int cop) {
		Vehicle veh = new Vehicle();
		veh.setProductId(productId);
		veh.setPricePerMile(vehicleRequest.getPrice());
		veh.setMake(vehicleRequest.getMake());
		veh.setModel(vehicleRequest.getModel());
		veh.setYear(vehicleRequest.getYear());
		veh.setAvailability(vehicleRequest.getAvailability());
		veh.setOdometer(vehicleRequest.getOdometer());
		vm.editVehicle(veh, id);
		return getVehicle(productId, id, 1);
	}
	
	public String deleteVehicle(int partnerId, int productId) {
		vm.deleteVehicle(productId, partnerId);
		return "OK";
	}
	
	public void vehicleLinks(VehicleRepresentation vehRep, int id, int cop) {
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		Link viewOwner = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + vehRep.getPartnerId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		Link self = new Link();
		
		switch(cop) {
		case 1:// Customer
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			Link rent = new Link("createOrder", "http://localhost:8081/OrderService/order?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			vehRep.setLinks(self, viewSiteInventory, viewOwner, rent);
			break;
		case 2:// Partner
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			if(vehRep.getPartnerId() == id) {
				Link edit = new Link("updateVehicle", "http://localhost:8081/PartnerService/partner/" + id + "inventory/" + vehRep.getVehicleId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link delete = new Link("deleteProduct", "http://localhost:8081/PartnerService/partner/" + id + "inventory/" + vehRep.getVehicleId() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				vehRep.setLinks(self, viewSiteInventory, edit, delete);
			}else {
				vehRep.setLinks(self, viewOwner, viewSiteInventory);
			}
			break;

		default:// Viewer
			vehRep.setLinks(self, viewSiteInventory, viewOwner);
			break;
		}
	}
}
