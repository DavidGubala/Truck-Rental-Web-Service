package com.truck.product.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class VehicleRepresentation {
	private int vehicleId;
	private String make;
	private String model;
	private int odometer;
	private String plateNumber;
	private String type;
	private String vin;
	private int year;
	
	public VehicleRepresentation() {}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
		
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
	
	public double getOdometer() {
		return odometer;
	}
	
	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

}
