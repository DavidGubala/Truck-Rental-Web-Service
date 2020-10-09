package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.user.Customer;
import com.truck.user.License;
import com.truck.user.Phone;

public class CustomerDAO {
    AddressDAO addDAO = new AddressDAO();
    
	// Create
	public void addCustomer(Customer cust) { 
		Connection con = DBHelper.getConnection();
        PreparedStatement custPst = null;

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
            
            addDAO.addAddress(cust.getHomeAddress(), cust.getCustomerId());
            addDAO.addAddress(cust.getBillingAddress(), cust.getCustomerId());
            
        } catch (SQLException ex) {

        } finally {

            try {
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
	// Read
	public Customer getCustomer(int customerId) {

		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
	    	//Get Customer
	    	st = con.createStatement();
	    	//String selectCustomerQuery = "SELECT ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, driverLicenseType, licenseNumber, licenseExpirattionDate FROM Customer WHERE customerID = '" + customerId + "'";
	    	String selectCustomerQuery = "SELECT * FROM Customer WHERE ID = '" + customerId + "'"; // I assume this does the same as above, will test later -d

	    	ResultSet custRS = st.executeQuery(selectCustomerQuery);      
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	      //Get Customer
    	  Customer customer = new Customer();
    	  License license = new License();
    	  Phone phone = new Phone();
    	  
	      while ( custRS.next() ) {														// Customer Info Gathered
	    	  customer.setCustomerId(custRS.getInt("ID"));								// ID
	    	  customer.setLastName(custRS.getString("firstName"));						// First Name
	    	  customer.setFirstName(custRS.getString("lastName"));						// Last Name
	    	  customer.setDateOfBirth(custRS.getDate("dateOfBirth"));				// Billing Address
	    	  phone.setNumber(custRS.getString("phoneNumber"));							// Phone Number
	    	  phone.setPhoneType(custRS.getString("phoneType"));						// Phone Type
	    	  customer.setPhone(phone);
	    	  customer.setEmail(custRS.getString("email"));												// Email Address
	    	  license.setLicenseType(custRS.getString("driverLicenseType"));			// Driver License Type/Classes
	    	  license.setLicenseNumber(custRS.getString("licenseNumber"));				// License Number
	    	  license.setExpirationDate(custRS.getDate("licenseExpirattionDate"));		// License Expiration Date
	    	  customer.setDriverLicense(license);
	      }
	      
	      custRS.close();
	      customer.setHomeAddress(addDAO.getAddress(custRS.getInt("homeAddressID")));
	      customer.setBillingAddress(addDAO.getAddress(custRS.getInt("billingAddressID")));
	      st.close();
	      return customer;
	    }	    
	    catch (SQLException se) {
	      System.err.println("CustomerDAO: Threw a SQLException retrieving the customer object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("CustomerDAO: Threw a SQLException saving the customer object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	  }
	// Update
	public void editCustomer( Customer cust) { 
		/*
		 * This can only be called by customers logged in, so when the customer chooses to edit their account information, 
		 * they will be met with a form prefilled with the data related to their account. They can then change whatever 
		 * information needs to be changed and send the put request as a new customer 
		 * 
		 * I think we have two options here:
		 * 1. Take in the new customer find the customer info in the database, compare each variable and make changes
		 * 2. Or delete the old customer data and replace with new Customer
		 * 
		 * --I chose Option 2 for now -David
		 * 
		 * need to first delete then create
		*/
		
        int customerId = cust.getCustomerId();
        deleteCustomer(customerId);
        addCustomer(cust);
	}
	// Delete
	public void deleteCustomer(int customerId) {
        Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	
            String custDELStm = "DELETE * FROM Customer WHERE ID = '" + customerId + "'";
            st.executeQuery(custDELStm);
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("CustomerDAO: Threw a SQLException saving the customer object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
        
}