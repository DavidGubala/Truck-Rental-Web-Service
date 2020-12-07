package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.domain.model.user.Partner;
import com.truck.domain.model.user.Phone;

public class PartnerDAO {
    AddressDAO addDAO = new AddressDAO();
    
	// Create
	public void addPartner(Partner part) {
		Connection con = DBHelper.getConnection();
        PreparedStatement partPst = null;

        try {
        	//Insert the Partner object
            //String partStm = "INSERT INTO Partner(ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        	String partStm = "INSERT INTO partners(ID, firstname, lastname, uname, pass) VALUES (?,?,?,?,?);";
        	partPst = con.prepareStatement(partStm);
            partPst.setInt(1, part.getPartnerId());
            partPst.setString(2, part.getFirstName());
            partPst.setString(3, part.getLastName());
            partPst.setString(4, part.getUserName());
            partPst.setString(5, part.getPassword());
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
	public Partner getPartnerbyID(int partnerId) {

		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
			//Get Partner
			st = con.createStatement();
			String selectPartnerQuery = "SELECT * FROM Partners WHERE ID = " + partnerId + ";";
			
			ResultSet PartRS = st.executeQuery(selectPartnerQuery);      
			System.out.println("PartnerDAO: *************** Query " + selectPartnerQuery);
	    	
			//Get Partner
			Partner Partner = new Partner();
			Phone phone = new Phone();
    	  
			while ( PartRS.next() ) {													// Partner Info Gathered
				Partner.setPartnerId(PartRS.getInt("ID"));								// ID
				Partner.setFirstName(PartRS.getString("firstName"));						// First Name
				Partner.setLastName(PartRS.getString("lastName"));
				Partner.setUserName(PartRS.getString("uname"));
				Partner.setPassword(PartRS.getString("pass"));
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
	
	public Partner getPartnerbyUser(String user) {

		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
			//Get Partner
			st = con.createStatement();
			String selectPartnerQuery = "SELECT * FROM Partners WHERE uname = '" + user + "';";
			
			ResultSet PartRS = st.executeQuery(selectPartnerQuery);      
			System.out.println("PartnerDAO: *************** Query " + selectPartnerQuery);
	    	
			//Get Partner
			Partner Partner = new Partner();
			Phone phone = new Phone();
    	  
			while ( PartRS.next() ) {													// Partner Info Gathered
				Partner.setPartnerId(PartRS.getInt("ID"));								// ID
				Partner.setFirstName(PartRS.getString("firstname"));						// First Name
				Partner.setLastName(PartRS.getString("lastname"));						// Last Name
				Partner.setUserName(PartRS.getString("uname"));
				Partner.setPassword(PartRS.getString("pass"));
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
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
			//Get Partner
			st = con.createStatement();
			String selectPartnerQuery = "SELECT * FROM Partners WHERE ID = " + partnerId + ";";
			
			ResultSet PartRS = st.executeQuery(selectPartnerQuery);      
			System.out.println("PartnerDAO: *************** Query " + selectPartnerQuery);
	    	
			//Get Partner
			Partner Partner = new Partner();
    	  
			while ( PartRS.next() ) {													// Partner Info Gathered
				Partner.setPartnerId(PartRS.getInt("ID"));								// ID
				Partner.setFirstName(PartRS.getString("firstName"));						// First Name
				Partner.setLastName(PartRS.getString("lastName"));
				Partner.setUserName(PartRS.getString("uname"));
				Partner.setPassword(PartRS.getString("pass"));
				//Partner.setDateOfBirth(PartRS.getDate("dateOfBirth"));				// DOB
				//phone.setNumber(PartRS.getString("phoneNumber"));						// Phone Number
				//phone.setPhoneType(PartRS.getString("phoneType"));					// Phone Type
				//Partner.setPhone(phone);
				//Partner.setEmail(PartRS.getString("email"));							// Email Address
			}
			
			//close to manage resources
			part.setUserName(Partner.getUserName());
			part.setPassword(Partner.getPassword());
			PartRS.close();
			st.close();
	    }	    
	    catch (SQLException se) {
	    	System.err.println("PartnerDAO: Threw a SQLException retrieving the Partner object.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    } finally {
			deletePartner(partnerId);
			addPartner(part);
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
	// Delete
	public void deletePartner(int partnerId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	
            String partDELStm = "DELETE FROM Partners WHERE ID = " + partnerId + ";";
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
