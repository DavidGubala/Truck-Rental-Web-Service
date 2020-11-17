package com.truck.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface PartnerService {

    //Partner CRUD
	public PartnerRepresentation getPartner(int id);
	public PartnerRepresentation createPartner(PartnerRequest PartnerRequest);
    public PartnerRepresentation updatePartner(int id,PartnerRequest PartnerRequest);
    public Response deletePartner(int id);
    
    //Partner-Product CRUD
    public List<VehicleRepresentation> getPartnerInventory(int id);
    public VehicleRepresentation addVehicle(VehicleRequest vehicleRequest, int partnerId);
    public VehicleRepresentation updateVehicle(VehicleRequest vehicleRequest, int productId, int partnerId);
    public Response deleteProduct(int productId, int partnerId);
}
