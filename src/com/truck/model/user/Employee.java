package com.truck.model.user;

public class Employee extends User{
	private int employeeId;
	private int ssn;
	private String employmentType;
	private String maritalStatus;
	private Benefits benefits;
	
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	public int getSsn() {
		return ssn; 
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int id) {
		this.employeeId = id;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatuse(String status) {
		this.maritalStatus = status;
	}
	
	public String getEmploymentType() {
		return employmentType;
	}
	
	public void setEmploymentType(String type) {
		this.employmentType = type;
	}
	
	public Benefits getBenefits() {
		return benefits;
	}
	
	public void setBenefits(Benefits benefits) {
		this.benefits = benefits;
	}
	
}
