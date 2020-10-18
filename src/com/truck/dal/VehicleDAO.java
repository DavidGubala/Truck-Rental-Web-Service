package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.product.Vehicle;

public class VehicleDAO {
	public void addVehicle(Vehicle veh, int partnerId) {
		Connection con = DBHelper.getConnection();
        PreparedStatement vehPst = null;

        try {
        	//Insert the Partner object
            //String prodStm = "INSERT INTO Vehicle(ID, PartnerID, vehicleType, price, plateNumber, make, model, year, availability, vin, odometer ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String prodStm = "INSERT INTO Vehicle(ID, PartnerID, price, make, model, year, availablility, odometer) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            vehPst = con.prepareStatement(prodStm);
            vehPst.setInt(1, veh.getProductId());
            vehPst.setInt(2, partnerId);
            vehPst.setDouble(3, veh.getPricePerMile());
            vehPst.setString(4, veh.getMake());
            vehPst.setString(5, veh.getModel());
            vehPst.setInt(6, veh.getYear());
            vehPst.setBoolean(7, veh.getAvailability()); 
            vehPst.setInt(8, veh.getOdometer());
            
            if(vehPst.toString().contains("?")){
                System.err.println("Statement still contains a ? and can't be executed");
            } else{
            	vehPst.executeUpdate();
            }
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (vehPst != null) {
                	vehPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("VehicleDAO: Threw a SQLException saving the Vehicle object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
	// Read
	public Vehicle getVehicle(int vehId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
	    	//Get Customer
	    	st = con.createStatement();
	    	String selectVehicleQuery = "SELECT * FROM Vehicle WHERE ID = '" + vehId + "'";
	    	
	    	ResultSet vehRS = st.executeQuery(selectVehicleQuery);      
	    	System.out.println("CustomerDAO: *************** Query " + selectVehicleQuery);
	    	
	      //Get Vehicle
    	  Vehicle vehicle = new Vehicle();
    	  
	      while ( vehRS.next() ) {														
	    	  vehicle.setProductId(vehRS.getInt("ID"));
	    	  //vehicle.setType(vehRS.getString("vehicleType"));
	    	  vehicle.setPricePerMile(vehRS.getDouble("price"));
	    	  //vehicle.setPlateNumber(vehRS.getString("plateNumber"));
	    	  vehicle.setMake(vehRS.getString("make"));
	    	  vehicle.setModel(vehRS.getString("model"));
	    	  vehicle.setYear(vehRS.getInt("year"));								
	    	  vehicle.setAvailability(vehRS.getBoolean("availablility"));
	    	  //vehicle.setVin(vehRS.getString("vin"));
	    	  vehicle.setOdometer(vehRS.getInt("odometer"));
	      }
	      
	      vehRS.close();
	      st.close();
	      return vehicle;
	    }	    
	    catch (SQLException se) {
	      System.err.println("VehicleDAO: Threw a SQLException retrieving the vehicle object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("VehicleDAO: Threw a SQLException saving the vehicle object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	}
	// Update
	public void editVehicle(Vehicle veh, int vehId) {
		deleteVehicle(veh.getProductId());
		addVehicle(veh, vehId);
	}	
	// Delete
	public void deleteVehicle(int vehId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	
            String vehDELStm = "DELETE * FROM Vehicle WHERE ID = '" + vehId + "'";
            st.executeQuery(vehDELStm);
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("VehicleDAO: Threw a SQLException saving the vehicle object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
}
