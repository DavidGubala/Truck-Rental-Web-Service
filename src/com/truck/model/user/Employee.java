package com.truck.model.user;

public class Employee {
	private int employeeId;
	private int ssn;
	
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
	
}
