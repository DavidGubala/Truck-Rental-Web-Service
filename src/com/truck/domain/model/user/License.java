package com.truck.domain.model.user;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class License {
	private String licenseType;
	private String licenseNumber;
	private Date expirationDate;
	private boolean expired;
	
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
	
	public boolean isExpired() {
		java.util.Date today = new Date(System.currentTimeMillis());
		java.util.Date sqlDate = new java.util.Date(expirationDate.getTime());
		
		if(today.compareTo(sqlDate) > 0) {
			expired = true;
		}else {
			expired = false;
		}
		
		return expired;
	}
}
