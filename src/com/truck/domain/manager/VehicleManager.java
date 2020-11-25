package com.truck.domain.manager;

import java.util.List;
import java.util.Random;

import com.truck.dal.VehicleDAO;
import com.truck.domain.model.product.Vehicle;

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
	public int addVehicle(Vehicle vehicle) {
		Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(100000);
		
		try {
			vehicle.setProductId(id);
			vehDAO.addVehicle(vehicle);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
		return id;
	}
	//Update
	public void editVehicle(Vehicle vehicle,int partnerId) {
		//Partner Validation
		if(vehDAO.validation(vehicle.getProductId(), partnerId)) {
			try {
				vehDAO.editVehicle(vehicle, partnerId);
			} catch (Exception se) {
				System.err.println("VehicleManager: Threw a Exception editing vehicle.");
				System.err.println(se.getMessage());
			}
		}
	}
	//Delete
	public void deleteVehicle(int vehId, int partnerId) {
		//Partner Validation
		if(vehDAO.validation(vehId, partnerId)) {
			try {
				vehDAO.deleteVehicle(vehId);
			} catch (Exception se) {
				System.err.println("VehicleManager: Threw a Exception deleting vehicle.");
				System.err.println(se.getMessage());
			}
		}
	}
	
	public List<Vehicle> getPartnerInventory(int id) {
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
