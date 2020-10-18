package com.truck.user.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.user.service.representation.*;

@WebService
public interface PartnerService {
	
		public PartnerRepresentation getPartner(int id);
		public PartnerRepresentation createPartner(PartnerRequest PartnerRequest);
	    public PartnerRepresentation updatePartner(int id,PartnerRequest PartnerRequest);
	    public Response deletePartner(int id);
}
