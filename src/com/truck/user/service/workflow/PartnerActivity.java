package com.truck.user.service.workflow;

import com.truck.user.Partner;
import com.truck.user.PartnerManager;
import com.truck.user.service.representation.PartnerRequest;
import com.truck.user.service.representation.PartnerRepresentation;

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
		
		//dao.deleteEmployee(id);
		pm.deletePartner(id);
		
		return "OK";
	}
	
}
