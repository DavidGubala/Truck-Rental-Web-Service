package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.user.Address;

public class AddressDAO {
	// Create
	public void addAddress(Address add, int userId) {
		Connection con = DBHelper.getConnection();
		PreparedStatement addPst = null;
		try {
			 String addStm = "INSERT INTO Address(userID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
			 addPst = con.prepareStatement(addStm);
			 addPst.setInt(1, userId);
			 addPst.setInt(2, add.getAddressId());  
			 addPst.setString(3, add.getStreet());       
			 addPst.setString(4, add.getUnit());  
			 addPst.setString(5, add.getCity());  
			 addPst.setString(6, add.getState());      
			 addPst.setString(7, add.getZip());  
			 addPst.executeUpdate();
		}catch (SQLException ex) {

        } finally {

            try {
                if (addPst != null) {
                	addPst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
      	      System.err.println("AddressDAO: Threw a SQLException saving the address object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
	// Read
	public Address getAddress(int addId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try {
	    	Address address =  new Address();
	    	st = con.createStatement();
	    	String selectAddressQuery = "SELECT * FROM Address WHERE ID = '" + addId + "'";

	    	ResultSet addRS = st.executeQuery(selectAddressQuery);      
	    	System.out.println("AddressDAO: *************** Query " + selectAddressQuery);
	    	while (addRS.next() ) {									// Home Address Info Gathered
	    		address.setStreet(addRS.getString("street"));		// Street
	    		address.setUnit(addRS.getString("unit"));			// Unit
	    		address.setCity(addRS.getString("city"));			// City
	    		address.setState(addRS.getString("state"));			// State
	    		address.setZip(addRS.getString("zip"));				// postal code
		      }
	    	
	    	addRS.close();
	    	return address;
	    } catch (SQLException se) {
		      System.err.println("AddressDAO: Threw a SQLException retrieving the address object.");
		      System.err.println(se.getMessage());
		      se.printStackTrace();
		    } finally {

	            try {
	                if (st != null) {
	                	st.close();
	                }

	            } catch (SQLException ex) {
	      	      System.err.println("AddressDAO: Threw a SQLException saving the address object.");
	    	      System.err.println(ex.getMessage());
	            }
		    }
		    return null;
	}
	// Update
	public void EditAddress(Address add, int userId) {
		int addressId = add.getAddressId();
		deleteAddress(addressId);
		addAddress(add, userId);
	}	
	// Delete
	public void deleteAddress(int addId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	
            String addDELStm = "DELETE * FROM Address WHERE ID = '" + addId + "'";
            st.executeQuery(addDELStm);
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("AddressDAO: Threw a SQLException saving the address object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
}
