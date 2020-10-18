package com.truck.product;

import com.truck.dal.VehicleDAO;

public class VehicleManager {
private VehicleDAO vehDAO = new VehicleDAO();
	//Read
	public Vehicle getVehicle(int vehicleId) {
		
		try {
			Vehicle vehicle = vehDAO.getVehicle(vehicleId);
			return vehicle;
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
		return null;
	}
	//Create
	public void addVehicle(Vehicle vehicle, int partnerId) {
		
		try {
			vehDAO.addVehicle(vehicle, partnerId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
	}
	//Update
	public void editVehicle(Vehicle vehicle,int partnerId) {
		try {
			vehDAO.editVehicle(vehicle, partnerId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception editing vehicle.");
			System.err.println(se.getMessage());
		}
	}
	//Delete
	public void deleteVehicle(int vehId) {
		try {
			vehDAO.deleteVehicle(vehId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception deleting vehicle.");
			System.err.println(se.getMessage());
		}
	}
}
