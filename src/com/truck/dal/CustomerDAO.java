package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.model.user.*;

public class CustomerDAO {
	public CustomerDAO() {}
	
	/* getCustomer Status:  Under Construction
	 * 
	 * We could work on getting customer information by more than just a customer id? -d
	 */
	public Customer getCustomer(String customerId) {
		 	 
	    try { 		
	    	//Get Customer
	    	Statement st = DBHelper.getConnection().createStatement();
	    	//String selectCustomerQuery = "SELECT ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, driverLicenseType, licenseNumber, licenseExpirattionDate FROM Customer WHERE customerID = '" + customerId + "'";
	    	String selectCustomerQuery = "SELECT * FROM Customer WHERE ID = '" + customerId + "'"; // I assume this does the same as above, will test later -d

	    	ResultSet custRS = st.executeQuery(selectCustomerQuery);      
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	      //Get Customer
    	  Customer customer = new Customer();
    	  License license = new License();
    	  Address homeAdd = new Address();
    	  Address billAdd = new Address();
    	  Phone phone = new Phone();
    	  
	      while ( custRS.next() ) {														// Customer Info Gathered
	    	  customer.setCustomerId(custRS.getInt("ID"));								// ID
	    	  customer.setLastName(custRS.getString("firstName"));						// First Name
	    	  customer.setFirstName(custRS.getString("lastName"));						// Last Name
	    	  customer.setDateOfBirth(custRS.getDate("dateOfBirth"));					// DOB
	    	  homeAdd.setAddressId(custRS.getInt("homeAddressID"));						// Home Address
	    	  billAdd.setAddressId(custRS.getInt("billingAddressID"));					// Billing Address
	    	  phone.setNumber(custRS.getString("phoneNumber"));							// Phone Number
	    	  phone.setPhoneType(custRS.getString("phoneType"));						// Phone Type
	    	  customer.setPhone(phone);
	    	  customer.setEmail("email");												// Email Address
	    	  license.setLicenseType(custRS.getString("driverLicenseType"));			// Driver License Type/Classes
	    	  license.setLicenseNumber(custRS.getString("licenseNumber"));				// License Number
	    	  license.setExpirationDate(custRS.getDate("licenseExpirattionDate"));		// License Expiration Date
	    	  customer.setDriverLicense(license);
	      }
	      //close to manage resources
	      custRS.close();
	      	    		  
	      //Get Home Address
	      String selectHomeAddressQuery = "SELECT * FROM Address WHERE ID = '" + customer.getHomeAddress().getAddressId() + "'";
	      ResultSet homeAddRS = st.executeQuery(selectHomeAddressQuery);
    	  
    	  System.out.println("CustomerDAO: *************** Query " + selectHomeAddressQuery);
    	  
	      while ( homeAddRS.next() ) {								// Home Address Info Gathered
	    	  homeAdd.setStreet(homeAddRS.getString("street"));		// Street
	    	  homeAdd.setUnit(homeAddRS.getString("unit"));			// Unit
	    	  homeAdd.setCity(homeAddRS.getString("city"));			// City
	    	  homeAdd.setState(homeAddRS.getString("state"));		// State
	    	  homeAdd.setZip(homeAddRS.getString("zip"));			// postal code
	      }
	      
	      customer.setHomeAddress(homeAdd);
	      //close to manage resources
	      homeAddRS.close();
	      
	      //Get Bill Address
	      String selectBillAddressQuery = "SELECT * FROM Address WHERE ID = '" + customer.getBillingAddress().getAddressId() + "'";
	      ResultSet billAddRS = st.executeQuery(selectBillAddressQuery);
    	  
    	  System.out.println("CustomerDAO: *************** Query " + selectBillAddressQuery);
    	  
	      while ( billAddRS.next() ) {								// Bill Address Info Gathered
	    	  billAdd.setStreet(billAddRS.getString("street"));		// Street
	    	  billAdd.setUnit(billAddRS.getString("unit"));			// Unit
	    	  billAdd.setCity(billAddRS.getString("city"));			// City
	    	  billAdd.setState(billAddRS.getString("state"));		// State
	    	  billAdd.setZip(billAddRS.getString("zip"));			// postal code
	      }
	      
	      customer.setBillingAddress(billAdd);
	      //close to manage resources
	      billAddRS.close();
	      st.close();
	      
	      return customer;
	    }	    
	    catch (SQLException se) {
	      System.err.println("CustomerDAO: Threw a SQLException retrieving the customer object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	
	
	
	
	public void addCustomer(Customer cust) {
		Connection con = DBHelper.getConnection();
        PreparedStatement custPst = null;
        PreparedStatement billAddPst = null;
        PreparedStatement homeAddPST = null;

        try {
        	//Insert the customer object
            String custStm = "INSERT INTO Customer(ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, driverLicenseType, licenseNumber, licenseExpirattionDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            custPst = con.prepareStatement(custStm);
            custPst.setInt(1, cust.getCustomerId());
            custPst.setString(2, cust.getFirstName());
            custPst.setString(3, cust.getLastName());
            custPst.setDate(4, cust.getDateOfBirth());
            custPst.setInt(5, cust.getHomeAddress().getAddressId());
            custPst.setInt(6, cust.getBillingAddress().getAddressId());
            custPst.setString(7, cust.getPhone().getNumber());
            custPst.setString(8, cust.getPhone().getPhoneType());
            custPst.setString(9, cust.getEmail()); 
            custPst.setString(10, cust.getDriverLicense().getLicenceType());
            custPst.setString(11, cust.getDriverLicense().getLicenceNumber());
            custPst.setDate(12, cust.getDriverLicense().getExpirationDate());
            custPst.executeUpdate();

        	//Insert the customer Billing Address object
            String billAddStm = "INSERT INTO Address(customerID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            billAddPst = con.prepareStatement(billAddStm);
            billAddPst.setInt(1, cust.getCustomerId());
            billAddPst.setInt(2, cust.getBillingAddress().getAddressId());  
            billAddPst.setString(3, cust.getBillingAddress().getStreet());       
            billAddPst.setString(4, cust.getBillingAddress().getUnit());  
            billAddPst.setString(5, cust.getBillingAddress().getCity());  
            billAddPst.setString(6, cust.getBillingAddress().getState());      
            billAddPst.setString(7, cust.getBillingAddress().getZip());  
            billAddPst.executeUpdate();
            
          //Insert the customer Home Address object
            String homeAddStm = "INSERT INTO Address(customerID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            homeAddPST = con.prepareStatement(homeAddStm);
            homeAddPST.setInt(1, cust.getCustomerId());
            homeAddPST.setInt(2, cust.getHomeAddress().getAddressId());  
            homeAddPST.setString(3, cust.getHomeAddress().getStreet());       
            homeAddPST.setString(4, cust.getHomeAddress().getUnit());
            homeAddPST.setString(5, cust.getHomeAddress().getCity());  
            homeAddPST.setString(6, cust.getHomeAddress().getState());      
            homeAddPST.setString(7, cust.getHomeAddress().getZip());
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
                if (custPst != null) {
                	custPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("CustomerDAO: Threw a SQLException saving the customer object.");
    	      System.err.println(ex.getMessage());
            }
        }
    }
}
