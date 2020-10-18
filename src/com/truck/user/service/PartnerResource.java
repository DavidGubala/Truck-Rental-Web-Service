package com.truck.user.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.truck.user.service.workflow.PartnerActivity;
import com.truck.user.service.representation.PartnerRepresentation;
import com.truck.user.service.representation.PartnerRequest;

@Path("/PartnerService/")
public class PartnerResource implements PartnerService {

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/partner")
	public PartnerRepresentation createPartner(PartnerRequest partnerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName());
		PartnerActivity partActivity = new PartnerActivity();
		return partActivity.createPartner(partnerRequest);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") int id) {
		System.out.println("GET METHOD Request from Client with PartnerID int ............." + id);
		PartnerActivity partActivity = new PartnerActivity();
		return partActivity.getPartner(id);
	}

	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public PartnerRepresentation updatePartner(@PathParam("partnerId") int id, PartnerRequest partnerRequest) {
		System.out.println("PUT METHOD Request from Client with ............." + id + "  " +partnerRequest.getFirstName() + "  " + partnerRequest.getLastName());
		PartnerActivity partActivity = new PartnerActivity();
		return partActivity.editPartner(id, partnerRequest);
	}

	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/partner/{partnerId}")
	public Response deletePartner(@PathParam("partnerId") int id) {
		System.out.println("DELETE METHOD Request from Client with PartnerID int ............." + id);
		PartnerActivity partActivity = new PartnerActivity();
		String res = partActivity.deletePartner(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}

}
