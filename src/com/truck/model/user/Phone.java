package com.truck.model.user;

public class Phone {
	private String phoneNumber;
	private String phoneType;
	
	public String getNumber() {
		return phoneNumber;
	}
	
	public void setNumber(String num) {
		this.phoneNumber = num;
	}
	
	public String getPhonType() {
		return phoneType;
	}
	
	public void setPhoneType(String type) {
		this.phoneType = type;
	}
}