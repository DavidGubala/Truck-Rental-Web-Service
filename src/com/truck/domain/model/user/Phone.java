package com.truck.domain.model.user;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Phone {
	private String phoneNumber;
	private String phoneType;
	
	public String getNumber() {
		return phoneNumber;
	}
	
	public void setNumber(String num) {
		this.phoneNumber = num;
	}
	
	public String getPhoneType() {
		return phoneType;
	}
	
	public void setPhoneType(String type) {
		this.phoneType = type;
	}
}
