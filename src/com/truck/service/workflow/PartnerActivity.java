package com.truck.service.workflow;

import com.truck.domain.manager.PartnerManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.user.Partner;
import com.truck.service.representation.PartnerRepresentation;
import com.truck.service.representation.PartnerRequest;

public class PartnerActivity{
	private static PartnerManager pm = new PartnerManager();
	
	public PartnerRepresentation createPartner(PartnerRequest partReq) {
		Partner newCust = new Partner();
		newCust.setFirstName(partReq.getFirstName());
		newCust.setLastName(partReq.getLastName());
		int partnerId = pm.addPartner(newCust);
		return getPartner(partnerId, partnerId, 2);
	}
	
	public PartnerRepresentation getPartner(int partnerId, int id, int cop) {
		
		Partner part = pm.getPartner(partnerId);
		PartnerRepresentation partRep = new PartnerRepresentation();
		partRep.setFirstName(part.getFirstName());
		partRep.setLastName(part.getLastName());
		partRep.setPartnerId(part.getPartnerId());
		
		partnerLinks(partRep, id, cop);
		
		return partRep;
	}
	
	public PartnerRepresentation editPartner(int partnerId, PartnerRequest partReq, int id, int cop) {
		Partner part = new Partner();
		part.setPartnerId(id);
		part.setFirstName(partReq.getFirstName());
		part.setLastName(partReq.getLastName());
		pm.editPartner(part);
		return getPartner(partnerId, id, cop);
	}
	
	public String deletePartner(int id) {
		pm.deletePartner(id);
		return "OK";
	}
	
	public void partnerLinks(PartnerRepresentation partRep, int id, int cop) {
		Link self = new Link();
		Link viewSiteInventory = new Link("getSiteInventory", "http://localhost:8081/VehicleService/vehicle?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
		Link viewPartnerInventory = new Link("getPartnerInventory", 
				"http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() +"/inventory"+"?id=" + id + "&cop=" + cop, 
				"application/vnd.truck+xml");
		
		switch(cop) {
		case 1:// Customer
			self = new Link("getCustomer", "http://localhost:8081/CustomerService/customer/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
			partRep.setLinks(self, viewSiteInventory, viewPartnerInventory);
			break;
		case 2:// Partner
			self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/" + id + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");

			if(partRep.getPartnerid() == id) {
				Link edit = new Link("updatePartner", "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link delete = new Link("deletePartner", "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");	
				Link viewOrders = new Link("getPartnerOrders",  "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "/orders" + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link add = new Link("addVehicle", "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "/inventory" + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");

				partRep.setLinks(self, viewSiteInventory, viewPartnerInventory, edit, delete, viewOrders, add);
			}else {
				partRep.setLinks(self, viewSiteInventory, viewPartnerInventory);
			}
			break;
		default:// Viewer
			partRep.setLinks(viewSiteInventory, viewPartnerInventory);
			break;
		}
	}
	
}
