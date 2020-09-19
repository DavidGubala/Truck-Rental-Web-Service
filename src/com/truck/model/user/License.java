package com.truck.model.user;

import java.time.LocalDateTime;

public class License {
	private String licenseType;
	private String licenseNumber;
	private LocalDateTime expirationDate;
	
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

	public LocalDateTime getDateOfBirth() {
		return expirationDate;
	}
	
	public void setDateOfBirth(LocalDateTime date) {
		this.expirationDate = date;
	}
}
