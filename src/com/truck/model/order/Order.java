package com.truck.model.order;

import com.truck.model.product.Product;
import com.truck.model.user.Customer;
import com.truck.model.order.OrderDetail;
import com.truck.model.order.Reservation;
import com.truck.model.product.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int orderId;
	private LocalDateTime orderDate;
	private String orderStatus="Open";
	private Customer customer;
	private Reservation reservaton;
	private Transaction transaction;
	private boolean paymentReceived;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int id) {
		this.orderId = id;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public LocalDateTime getDate() {
		return orderDate;
	}
	
	public void setDate(LocalDateTime date) {
		this.orderDate = date;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String status) {
		this.orderStatus = status;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCostumer(Customer customer) {
		this.customer = customer;
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
	public boolean isPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	
	public void addProduct(Product product, int quantity) {
		if (orderStatus.equals("Open")) {
			orderDetails.add(new OrderDetail());
		} else {
			throw new IllegalStateException("Can only add product in Open state.");
		}
	}
	
	public void cancelOrder() {
		if (orderStatus.equals("Open") || orderStatus.equals("Ordered")) {
			orderStatus = "Canceled";
		} else {
			throw new IllegalStateException("Cannot cancel order in this state.");
		}
	}
	
	public void confirmOrder() {
		if (getOrderDetails().isEmpty()) {
			orderStatus = "Canceled";
		} else if (orderStatus.equals("Open")) {
			orderStatus = "Ordered";
		} else {
			throw new IllegalStateException("Cannot confirm order in this state.");
		}
	}
	
	
	public void orderPayed() {
		if (orderStatus.equals("Ordered")) {
			setPaymentReceived(true);
		} else {
			throw new IllegalStateException("Cannot pay in this state.");
		}
	}
	
	
	public double getOrderTotal() {
		double total = 0.00;
		for (OrderDetail line : orderDetails) {
			total += line.getProduct().getPricePerMile() * line.getQuantity();
		}
		
		return total;
	}
}
