package com.truck.domain.model.order;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
