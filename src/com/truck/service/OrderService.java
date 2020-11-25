package com.truck.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import com.truck.service.representation.*;

@WebService
public interface OrderService {
	
	public OrderRepresentation getOrder(int orderId, int id, int cop);
	public OrderRepresentation createOrder(OrderRequest OrderRequest, int id, int cop);
	public OrderRepresentation updateOrder(int orderId, int id, OrderRequest OrderRequest);
	public Response deleteOrder(int id);
	
}
