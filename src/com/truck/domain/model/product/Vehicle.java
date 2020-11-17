package com.truck.domain.model.product;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehicle extends Product {
	private String plateNumber;
	private String make;
	private String model;
	private int year;
	private int odometer;
	private String vin;
	private String type;
	private String available;
	

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getAvailability() {
		return available;
	}
	
	public void setAvailability(String available) {
		this.available = available;
	}
}