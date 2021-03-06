package com.truck.domain.model.order;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
	private int orderId; 				// Each order located by and ID
	private Date orderDate;	// Date Order was placed
	private String orderStatus="Open";	// Current Status of Order
	private int customerId;			// Customer the order is for
	private int vehicleId;			// Product the Customer is ordering
	private int partnerId;			// Product the Customer is ordering
	private Reservation reservaton;		// Reservation information 
	private Transaction transaction;	// Transaction information
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int id) {
		this.orderId = id;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(int id) {
		this.vehicleId = id;
	}
	
	public Date getDate() {
		return orderDate;
	}
	
	public void setDate(Date date) {
		this.orderDate = date;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int id) {
		this.customerId = id;
	}
	
	public int getPartnerId() {
		return partnerId;
	}
	
	public void setPartnerId(int id) {
		this.partnerId = id;
	}

	public Reservation getReservation() {
		return reservaton;
	}
	
	public void setReservation(Reservation reservaton) {
		this.reservaton = reservaton;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public String setOrderStatus(String status) {
		this.orderStatus = status;
		return orderStatus;
	}
	
	public void orderCancel() { //  Cancel order
		if (getOrderStatus().equals("Ordered") || getOrderStatus().equals("Ready")) {
			setOrderStatus("Canceled");
		} else {
			throw new IllegalStateException("Cannot cancel order in this state.");
		}
	}
	
	public void orderReady() { // Order payment has been processed/ or alternatively the Order is just ready for pick up. After this we would give the customer reservation information
		if (getOrderStatus().equals("Ordered")) {
			setOrderStatus("Ready");
		} else {
			throw new IllegalStateException("Cannot ready order in this state.");
		}
	}
	
	public void orderShipping() { // Shipping would reference the time at which the customer has picked up the vehicle and is currently using it.
		if (getOrderStatus().equals("Ready")) {
			setOrderStatus("Shipping");
		} else {
			throw new IllegalStateException("Cannot ship order in this state.");
		}
	}
	
	public void orderReturn() { // Return would reference the time at which the customer returns the vehicle to drop-off location.
		if (getOrderStatus().equals("Shipping")) {
			setOrderStatus("Return");
		} else {
			throw new IllegalStateException("Cannot return order in this state.");
		}
	}
}
