package com.truck.model.order;

import com.truck.model.product.Product;
import com.truck.model.user.Customer;
import com.truck.model.order.Reservation;
import java.time.LocalDateTime;

public class Order {
	private int orderId;
	private LocalDateTime orderDate;
	private String orderStatus;
	
	private Customer customer;
	private Product product;
	private Reservation reservaton;
	private Transaction transaction;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int id) {
		this.orderId = id;
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
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
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
}
