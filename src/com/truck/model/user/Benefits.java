package com.truck.model.user;

public class Benefits {
	private int benefitId;
	private boolean medical;
	private boolean dental;
	
	public void setBenefitId(int id) {
		this.benefitId = id;
	}
	
	public int getBenefitId() {
		return benefitId;
	}
	
	public void setMedical(boolean medical) {
		this.medical = medical;
	}
	
	public boolean getMedical() {
		return medical;
	}
	
	public void setDental(boolean dental) {
		this.dental = dental;
	}
	
	public boolean getDental() {
		return dental;
	}
}
