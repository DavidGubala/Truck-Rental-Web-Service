package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.user.Partner;
import com.truck.user.Phone;

public class PartnerDAO {
    AddressDAO addDAO = new AddressDAO();
    
	// Create
	public void addPartner(Partner part) {
		Connection con = DBHelper.getConnection();
        PreparedStatement partPst = null;

        try {
        	//Insert the Partner object
            //String partStm = "INSERT INTO Partner(ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        	String partStm = "INSERT INTO Partner(ID, firstName, lastName) VALUES (?,?,?);";
        	partPst = con.prepareStatement(partStm);
            partPst.setInt(1, part.getPartnerId());
            partPst.setString(2, part.getFirstName());
            partPst.setString(3, part.getLastName());
            //partPst.setDate(4, part.getDateOfBirth());
            //partPst.setInt(5, part.getHomeAddress().getAddressId());
            //partPst.setInt(6, part.getBillingAddress().getAddressId());
            //partPst.setString(7, part.getPhone().getNumber());
            //partPst.setString(8, part.getPhone().getPhoneType());
            //partPst.setString(9, part.getEmail());
            
            if(partPst.toString().contains("?")){
                System.err.println("Statement still contains a ? and can't be executed");
            } else{
            	partPst.executeUpdate();
            }
            
            //addDAO.addAddress(part.getHomeAddress(), part.getPartnerId());
            //addDAO.addAddress(part.getBillingAddress(), part.getPartnerId());
            
            
        } catch (SQLException ex) {

        } finally {

            try {
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
			String selectPartnerQuery = "SELECT * FROM Partner WHERE ID = " + partnerId + ";";
			
			ResultSet PartRS = st.executeQuery(selectPartnerQuery);      
			System.out.println("PartnerDAO: *************** Query " + selectPartnerQuery);
	    	
			//Get Partner
			Partner Partner = new Partner();
			Phone phone = new Phone();
    	  
			while ( PartRS.next() ) {													// Partner Info Gathered
				Partner.setPartnerId(PartRS.getInt("ID"));								// ID
				Partner.setLastName(PartRS.getString("firstName"));						// First Name
				Partner.setFirstName(PartRS.getString("lastName"));						// Last Name
				//Partner.setDateOfBirth(PartRS.getDate("dateOfBirth"));				// DOB
				//phone.setNumber(PartRS.getString("phoneNumber"));						// Phone Number
				//phone.setPhoneType(PartRS.getString("phoneType"));					// Phone Type
				//Partner.setPhone(phone);
				//Partner.setEmail(PartRS.getString("email"));							// Email Address
			}
			//close to manage resources
			PartRS.close();
	      	//Partner.setHomeAddress(addDAO.getAddress(PartRS.getInt("homeAddressID")));
	      	//Partner.setBillingAddress(addDAO.getAddress(PartRS.getInt("homeAddressID")));
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
