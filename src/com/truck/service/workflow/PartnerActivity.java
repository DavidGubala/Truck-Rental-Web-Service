package com.truck.service.workflow;

import java.util.ArrayList;
import java.util.List;

import com.truck.domain.manager.PartnerManager;
import com.truck.domain.manager.VehicleManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.product.Vehicle;
import com.truck.domain.model.user.Partner;
import com.truck.service.representation.PartnerRepresentation;
import com.truck.service.representation.PartnerRequest;
import com.truck.service.representation.VehicleRepresentation;

public class PartnerActivity {
	private static PartnerManager pm = new PartnerManager();
	private static VehicleManager vm = new VehicleManager();
	
	public PartnerRepresentation createPartner(PartnerRequest partReq) {
		Partner newCust = new Partner();
		newCust.setFirstName(partReq.getFirstName());
		newCust.setLastName(partReq.getLastName());
		int id = pm.addPartner(newCust);
		return getPartner(id);
	}
	
	public PartnerRepresentation getPartner(int partnerId) {
		
		Partner part = pm.getPartner(partnerId);
		PartnerRepresentation partRep = new PartnerRepresentation();
		partRep.setFirstName(part.getFirstName());
		partRep.setLastName(part.getLastName());
		partRep.setPartnerId(part.getPartnerId());

		//Partner Links
		Link edit = new Link("edit", 
				"http://localhost:8081/PartnerService/partner/?partnerId" + partRep.getPartnerid());
		Link delete = new Link("delete", 
				"http://localhost:8081/PartnerService/partner/?partnerId" + partRep.getPartnerid());	
		Link viewOrders = new Link("view orders", 
				"http://localhost:8081/OrderService/order?customer+vehicle" + vehRep.getVehicleId());	
		Link viewInventory = new Link("view inventory", 
				"http://localhost:8081/OrderService/order?customer+vehicle" + vehRep.getVehicleId());	
		partRep.setLinks(edit,edit);
		
		
		return partRep;
	}
	
	public PartnerRepresentation editPartner(int id, PartnerRequest partReq) {
		Partner part = new Partner();
		part.setPartnerId(id);
		part.setFirstName(partReq.getFirstName());
		part.setLastName(partReq.getLastName());
		pm.editPartner(part);
		return getPartner(id);
	}
	
	public String deletePartner(int id) {
		pm.deletePartner(id);
		return "OK";
	}
	
	public List<VehicleRepresentation> getInventory(int id){
		List<Vehicle> inv = vm.getPartnerInventory(id);
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
}
