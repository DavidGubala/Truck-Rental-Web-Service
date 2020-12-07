package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface PartnerService {
	
	public Response loginPartner(LoginRequest lr);
	
    //Partner CRUD
	public Response getPartner(int partnerId, int id, int cop);
	public Response createPartner(PartnerRequest PartnerRequest);
    public Response updatePartner(int customerId, PartnerRequest PartnerRequest, int id, int cop);
    public Response deletePartner(int id);
    
    public Response getPartnerOrders(int partnerId,int id, int cop);
    
    //Partner-Product CRUD
    public Response getPartnerInventory(int partnerId, int id, int cop);
    public Response addVehicle(VehicleRequest vehicleRequest, int partnerId, int id, int cop);
    public Response updateVehicle(VehicleRequest vehicleRequest, int productId, int partnerId, int id, int cop);
    public Response deleteProduct(int productId, int partnerId);
}
