package com.truck.product;

import java.util.List;
import java.util.Random;

import com.truck.dal.VehicleDAO;

public class VehicleManager {
private VehicleDAO vehDAO = new VehicleDAO();
	//Read
	public Vehicle getVehicle(int vehicleId) {
		
		try {
			return vehDAO.getVehicle(vehicleId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
		return null;
	}
	//Create
	public int addVehicle(Vehicle vehicle, int partnerId) {
		Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(100000);
		
		try {
			vehicle.setProductId(id);
			vehDAO.addVehicle(vehicle, partnerId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
		return id;
	}
	//Update
	public void editVehicle(Vehicle vehicle,int partnerId) {
		//Partner Validation
		try {
			vehDAO.editVehicle(vehicle, partnerId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception editing vehicle.");
			System.err.println(se.getMessage());
		}
	}
	//Delete
	public void deleteVehicle(int vehId, int partnerId) {
		//Partner Validation
		try {
			vehDAO.deleteVehicle(vehId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception deleting vehicle.");
			System.err.println(se.getMessage());
		}
	}
	
	public List<Vehicle> getPartnerInventory(int id) {
		//Partner Validation
		try {
			return vehDAO.getPartnerInventory(id);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception getting partner vehicle inventory.");
			System.err.println(se.getMessage());
		}
		return null;
	}
	
	public List<Vehicle> getSiteInventory() {
		try {
			return vehDAO.getSiteInventory();
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception getting entire vehicle inventory.");
			System.err.println(se.getMessage());
		}
		return null;
	}
}
