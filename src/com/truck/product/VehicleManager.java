package com.truck.product;

import com.truck.dal.VehicleDAO;

public class VehicleManager {
private VehicleDAO vehDAO = new VehicleDAO();
	
	public Vehicle findPVehicleById(int vehicleId) {
		
		try {
			Vehicle vehicle = vehDAO.getVehicle(vehicleId);
			return vehicle;
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
		return null;
	}
	
	public void addVehicle(Vehicle vehicle, int partnerId) {
		
		try {
			vehDAO.addVehicle(vehicle, partnerId);
		} catch (Exception se) {
			System.err.println("VehicleManager: Threw a Exception retrieving vehicle.");
			System.err.println(se.getMessage());
		}
	}
}
