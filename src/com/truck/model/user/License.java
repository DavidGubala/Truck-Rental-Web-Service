package com.truck.model.user;

import java.sql.Date;

public class License {
	private String licenseType;
	private String licenseNumber;
	private Date expirationDate;
	
	public String getLicenceType() {
		return licenseType;
	}
	
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	
	public String getLicenceNumber() {
		return licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date date) {
		this.expirationDate = date;
	}
}
