package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.user.Address;
import com.truck.user.Partner;
import com.truck.user.License;
import com.truck.user.Partner;
import com.truck.user.Phone;

public class PartnerDAO {
	/*
	 * Under Construction
	 * */
	// Create
	public void addPartner(Partner part) {
		Connection con = DBHelper.getConnection();
        PreparedStatement partPst = null;
        PreparedStatement billAddPst = null;
        PreparedStatement homeAddPST = null;

        try {
        	//Insert the Partner object
            String partStm = "INSERT INTO Partner(ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, inventoryId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            partPst = con.prepareStatement(partStm);
            partPst.setInt(1, part.getPartnerId());
            partPst.setString(2, part.getFirstName());
            partPst.setString(3, part.getLastName());
            partPst.setDate(4, part.getDateOfBirth());
            partPst.setInt(5, part.getHomeAddress().getAddressId());
            partPst.setInt(6, part.getBillingAddress().getAddressId());
            partPst.setString(7, part.getPhone().getNumber());
            partPst.setString(8, part.getPhone().getPhoneType());
            partPst.setString(9, part.getEmail());
            partPst.setInt(10, part.getInventoryId());
            partPst.executeUpdate();

        	//Insert the Partner Billing Address object
            String billAddStm = "INSERT INTO Address(PartnerID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            billAddPst = con.prepareStatement(billAddStm);
            billAddPst.setInt(1, part.getPartnerId());
            billAddPst.setInt(2, part.getBillingAddress().getAddressId());  
            billAddPst.setString(3, part.getBillingAddress().getStreet());       
            billAddPst.setString(4, part.getBillingAddress().getUnit());  
            billAddPst.setString(5, part.getBillingAddress().getCity());  
            billAddPst.setString(6, part.getBillingAddress().getState());      
            billAddPst.setString(7, part.getBillingAddress().getZip());  
            billAddPst.executeUpdate();
            
          //Insert the Partner Home Address object
            String homeAddStm = "INSERT INTO Address(PartnerID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            homeAddPST = con.prepareStatement(homeAddStm);
            homeAddPST.setInt(1, part.getPartnerId());
            homeAddPST.setInt(2, part.getHomeAddress().getAddressId());  
            homeAddPST.setString(3, part.getHomeAddress().getStreet());       
            homeAddPST.setString(4, part.getHomeAddress().getUnit());
            homeAddPST.setString(5, part.getHomeAddress().getCity());  
            homeAddPST.setString(6, part.getHomeAddress().getState());      
            homeAddPST.setString(7, part.getHomeAddress().getZip());
            homeAddPST.executeUpdate();
        } catch (SQLException ex) {

        } finally {

            try {
                if (billAddPst != null) {
                	billAddPst.close();
                }
                if (homeAddPST != null) {
                	homeAddPST.close();
                }
                if (partPst != null) {
                	partPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("PartnerDAO: Threw a SQLException saving the Partner object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
	// Read
	public Partner getPartner(int partnerId) {

		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
	    	//Get Partner
	    	st = con.createStatement();
	    	//String selectPartnerQuery = "SELECT ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, driverLicenseType, licenseNumber, licenseExpirattionDate FROM Partner WHERE PartnerID = '" + PartnerId + "'";
	    	String selectPartnerQuery = "SELECT * FROM Partner WHERE ID = '" + partnerId + "'"; // I assume this does the same as above, will test later -d

	    	ResultSet PartRS = st.executeQuery(selectPartnerQuery);      
	    	System.out.println("PartnerDAO: *************** Query " + selectPartnerQuery);
	    	
	      //Get Partner
    	  Partner Partner = new Partner();
    	  License license = new License();
    	  Address homeAdd = new Address();
    	  Address billAdd = new Address();
    	  Phone phone = new Phone();
    	  
	      while ( PartRS.next() ) {														// Partner Info Gathered
	    	  Partner.setPartnerId(PartRS.getInt("ID"));								// ID
	    	  Partner.setLastName(PartRS.getString("firstName"));						// First Name
	    	  Partner.setFirstName(PartRS.getString("lastName"));						// Last Name
	    	  Partner.setDateOfBirth(PartRS.getDate("dateOfBirth"));					// DOB
	    	  homeAdd.setAddressId(PartRS.getInt("homeAddressID"));						// Home Address
	    	  billAdd.setAddressId(PartRS.getInt("billingAddressID"));					// Billing Address
	    	  phone.setNumber(PartRS.getString("phoneNumber"));							// Phone Number
	    	  phone.setPhoneType(PartRS.getString("phoneType"));						// Phone Type
	    	  Partner.setPhone(phone);
	    	  Partner.setEmail(PartRS.getString("email"));												// Email Address
	    	  Partner.setInvenotoryId(PartRS.getInt("inventoryId"));
	      }
	      //close to manage resources
	      PartRS.close();
	      	    		  
	      //Get Home Address
	      String selectHomeAddressQuery = "SELECT * FROM Address WHERE ID = '" + Partner.getHomeAddress().getAddressId() + "'";
	      ResultSet homeAddRS = st.executeQuery(selectHomeAddressQuery);
    	  
    	  System.out.println("PartnerDAO: *************** Query " + selectHomeAddressQuery);
    	  
	      while ( homeAddRS.next() ) {								// Home Address Info Gathered
	    	  homeAdd.setStreet(homeAddRS.getString("street"));		// Street
	    	  homeAdd.setUnit(homeAddRS.getString("unit"));			// Unit
	    	  homeAdd.setCity(homeAddRS.getString("city"));			// City
	    	  homeAdd.setState(homeAddRS.getString("state"));		// State
	    	  homeAdd.setZip(homeAddRS.getString("zip"));			// postal code
	      }
	      
	      Partner.setHomeAddress(homeAdd);
	      //close to manage resources
	      homeAddRS.close();
	      
	      //Get Bill Address
	      String selectBillAddressQuery = "SELECT * FROM Address WHERE ID = '" + Partner.getBillingAddress().getAddressId() + "'";
	      ResultSet billAddRS = st.executeQuery(selectBillAddressQuery);
    	  
    	  System.out.println("PartnerDAO: *************** Query " + selectBillAddressQuery);
    	  
	      while ( billAddRS.next() ) {								// Bill Address Info Gathered
	    	  billAdd.setStreet(billAddRS.getString("street"));		// Street
	    	  billAdd.setUnit(billAddRS.getString("unit"));			// Unit
	    	  billAdd.setCity(billAddRS.getString("city"));			// City
	    	  billAdd.setState(billAddRS.getString("state"));		// State
	    	  billAdd.setZip(billAddRS.getString("zip"));			// postal code
	      }
	      
	      Partner.setBillingAddress(billAdd);
	      //close to manage resources
	      billAddRS.close();
	      st.close();
	      
	      return Partner;
	    }	    
	    catch (SQLException se) {
	      System.err.println("PartnerDAO: Threw a SQLException retrieving the Partner object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("PartnerDAO: Threw a SQLException saving the Partner object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	}
	// Update
	public void editPartner(Partner part) {
		int partnerId = part.getPartnerId();
		deletePartner(partnerId);
		addPartner(part);
	}	
	// Delete
	public void deletePartner(int partnerId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	
            String partDELStm = "DELETE * FROM Partner WHERE ID = '" + partnerId + "'";
            st.executeQuery(partDELStm);
            
            String homeAddressDELStm = "DELETE * FROM Address WHERE PartnerID = '" + partnerId + "'";
            st.executeQuery(homeAddressDELStm);
            
            String billAddDELStm = "DELETE * FROM Address WHERE PartnerID = '" + partnerId + "'";
            st.executeQuery(billAddDELStm);
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("PartnerDAO: Threw a SQLException saving the Partner object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
}
