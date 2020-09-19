package com.truck.model.order;

import java.time.LocalDateTime;

public class Transaction {
	private int trasactionId;
	private LocalDateTime transactionDate;
	private String transactionStatus;
	
	public int getTrasactionId() {
		return trasactionId;
	}

	public void setTrasactionId(int id) {
		this.trasactionId = id;
	}
	
	public LocalDateTime getDate() {
		return transactionDate;
	}
	
	public void setDate(LocalDateTime date) {
		this.transactionDate = date;
	}
	
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTrasactionStatus(String status) {
		this.transactionStatus = status;
	}
	
}
