package com.truck.service.workflow;

import com.truck.domain.manager.PartnerManager;
import com.truck.domain.model.Link;
import com.truck.domain.model.user.Partner;
import com.truck.service.representation.PartnerRepresentation;
import com.truck.service.representation.PartnerRequest;

public class PartnerActivity {
	private static PartnerManager pm = new PartnerManager();
	
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
		
		partnerLinks(partRep);
		
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
	
	public void partnerLinks(PartnerRepresentation partRep) {
		Link self = new Link("getPartner", "http://localhost:8081/PartnerService/partner/?partnerId=" + partRep.getPartnerid(), "xml");
		Link edit = new Link("updatePartner", "http://localhost:8081/PartnerService/partner/?partnerId=" + partRep.getPartnerid(), "xml");
		Link delete = new Link("deletePartner", "http://localhost:8081/PartnerService/partner/?partnerId=" + partRep.getPartnerid(), "xml");	
		Link viewOrders = new Link("getPartnerOrders",  "http://localhost:8081/PartnerService/partner/?partnerId=" + partRep.getPartnerid() + "/orders", "xml");
		Link viewInventory = new Link("getPartnerInventory", "http://localhost:8081/PartnerService/partner/?partnerId=" + partRep.getPartnerid() + "/inventory", "xml");
		Link add = new Link("addVehicle", "http://localhost:8081/PartnerService/partner/?partnerId=" + partRep.getPartnerid() + "/inventory", "xml");
		partRep.setLinks(self, edit, delete, viewOrders, viewInventory, add);
	}
	
}
