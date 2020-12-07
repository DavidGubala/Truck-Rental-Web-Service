package com.truck.service.workflow;

import com.truck.domain.manager.PartnerManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.user.Partner;
import com.truck.service.representation.PartnerRepresentation;
import com.truck.service.representation.PartnerRequest;

public class PartnerActivity{
	private static PartnerManager pm = new PartnerManager();
	
	public PartnerRepresentation createPartner(PartnerRequest partReq) {
		Partner newPart = new Partner();
		newPart.setFirstName(partReq.getFirstName());
		newPart.setLastName(partReq.getLastName());
		newPart.setUserName(partReq.getUserName());
		newPart.setPassword(partReq.getPassword());
		int partnerId = pm.addPartner(newPart);
		return getPartner(partnerId, partnerId, 2);
	}
	
	public PartnerRepresentation getPartner(int partnerId, int id, int cop) {
		
		Partner part = pm.getPartnerbyID(partnerId);
		PartnerRepresentation partRep = new PartnerRepresentation();
		partRep.setFirstName(part.getFirstName());
		partRep.setLastName(part.getLastName());
		partRep.setPartnerId(part.getPartnerId());
		partnerLinks(partRep, id, cop);
		
		return partRep;
	}
	
	public int login(String user, String pass) {
		Partner part = pm.getPartnerbyUser(user);
		if(part.getPassword().equals(pass)) {
			return part.getPartnerId();
		}else {
			return 0;
		}
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
		Link viewPartnerInventory = new Link("View Partner's Inventory", 
				"http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() +"/inventory"+"?id=" + id + "&cop=" + cop, 
				"application/vnd.truck+xml");
		
		switch(cop) {
		case 1:// Customer
			partRep.setLinks(viewPartnerInventory);
			break;
		case 2:// Partner
			if(partRep.getPartnerid() == id) {
				Link edit = new Link("Edit Partner", "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link delete = new Link("Delete Partner", "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link add = new Link("Add Vehicle", "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "/inventory" + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				Link viewOrders = new Link("Get Orders",  "http://localhost:8081/PartnerService/partner/" + partRep.getPartnerid() + "/orders" + "?id=" + id + "&cop=" + cop, "application/vnd.truck+xml");
				partRep.setLinks(viewPartnerInventory, edit, delete, viewOrders, add);
			}else {
				partRep.setLinks(viewPartnerInventory);
			}
			break;
		default:// Viewer
			partRep.setLinks(viewPartnerInventory);
			break;
		}
	}
	
}
