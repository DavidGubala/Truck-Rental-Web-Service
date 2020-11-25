package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface PartnerService {

    //Partner CRUD
	public PartnerRepresentation getPartner(int partnerId, int id, int cop);
	public PartnerRepresentation createPartner(PartnerRequest PartnerRequest);
    public PartnerRepresentation updatePartner(int customerId, PartnerRequest PartnerRequest, int id, int cop);
    public Response deletePartner(int id);
    
    public ListRepresentation getPartnerOrders(int partnerId,int id, int cop);
    
    //Partner-Product CRUD
    public ListRepresentation getPartnerInventory(int partnerId, int id, int cop);
    public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, int partnerId, int id, int cop);
    public VehicleRepresentation updateVehicle(VehicleRequest vehicleRequest, int productId, int partnerId, int id, int cop);
    public Response deleteProduct(int productId, int partnerId);
}
