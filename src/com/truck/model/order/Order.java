package com.truck.model.order;

import java.util.ArrayList;
import java.util.List;
import com.truck.model.item.Product;

public class Order {
	private String orderID;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	private Product product;
	private String date;        //When did they order
	private String duration;    //How long they will rent
	//private int quantity;
	private String orderState = "Open";
	private boolean paymentReceived;

	public Order() {}
	
//	public OrderDetail(Product product, int quantity) {
//		this.product = product;
//		this.quantity = quantity;
//	}
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderId) {
		this.orderID = orderId;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getOrderState() {
		return orderState;
	}
	
	public boolean isPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	
	public void addProduct(Product product, int quantity) {
		if (orderState.equals("Open")) {
			orderDetails.add(new OrderDetail(product, quantity));
		} else {
			throw new IllegalStateException("Can only add product in Open state.");
		}
	}
	
	public void cancelOrder() {
		if (orderState.equals("Open") || orderState.equals("Ordered")) {
			orderState = "Canceled";
		} else {
			throw new IllegalStateException("Cannot cancel order in this state.");
		}
	}
	
	public void confirmOrder() {
		if (getOrderDetails().isEmpty()) {
			orderState = "Canceled";
		} else if (orderState.equals("Open")) {
			orderState = "Ordered";
		} else {
			throw new IllegalStateException("Cannot confirm order in this state.");
		}
	}
	
	
	public void orderPayed() {
		if (orderState.equals("Ordered")) {
			setPaymentReceived(true);
		} else {
			throw new IllegalStateException("Cannot pay in this state.");
		}
	}
	
	
	public boolean isFinished() {
		if (orderState.equals("Delivered") || orderState.equals("Canceled")) {
			return true;
		}
		return false;
	}
	
	public double getOrderTotal() {
		double total = 0.00;
		for (OrderDetail line : orderDetails) {
			total += line.getProduct().getPricePerMile() * line.getQuantity();
		}
		return total;
	}

}
	
//	public int getQuantity() {
//		return quantity;
//	}
//	
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}


