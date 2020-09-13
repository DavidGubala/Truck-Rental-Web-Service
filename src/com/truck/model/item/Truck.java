package com.truck.model.item;

public class Truck extends Product {
	private String truckID;
	private String make;
	private String year;
	private String plateNumber;
	
	public Truck() {}
	
	public String getTruckID() {
		return truckID;
	}
	
	public void setTruckID(String truckID) {
		this.truckID = truckID;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	public String getPlateNumber() {
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
}
