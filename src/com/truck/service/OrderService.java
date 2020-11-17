package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface OrderService {
	
	public OrderRepresentation getOrderId(int id);
	public OrderRepresentation createOrder(OrderRequest OrderRequest);
	public OrderRepresentation updateOrder(int id, OrderRequest OrderRequest);
	public Response deleteOrder(int id);
	
}
