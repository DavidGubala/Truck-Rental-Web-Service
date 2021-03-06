package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.domain.model.user.Customer;
import com.truck.domain.model.user.License;
import com.truck.domain.model.user.Phone;

public class CustomerDAO {
    AddressDAO addDAO = new AddressDAO();
    
	// Create
	public void addCustomer(Customer cust) { 
		Connection con = DBHelper.getConnection();
        PreparedStatement custPst = null;
        try {
        	//Insert the customer object
            //String custStm = "INSERT INTO Customer(ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, driverLicenseType, licenseNumber, licenseExpirattionDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
        	String custStm = "INSERT INTO Customers(ID, firstName, lastName, uname, pass) VALUES(?, ?, ?, ?, ?);";
            custPst = con.prepareStatement(custStm);
            custPst.setInt(1, cust.getCustomerId());
            custPst.setString(2, cust.getFirstName());
            custPst.setString(3, cust.getLastName());
            custPst.setString(4, cust.getUserName());
            custPst.setString(5, cust.getPassword());
            //custPst.setDate(4, cust.getDateOfBirth());
            //custPst.setInt(5, cust.getHomeAddress().getAddressId());
            //custPst.setInt(6, cust.getBillingAddress().getAddressId());
            //custPst.setString(7, cust.getPhone().getNumber());
            //custPst.setString(8, cust.getPhone().getPhoneType());
            //custPst.setString(9, cust.getEmail()); 
            //custPst.setString(10, cust.getDriverLicense().getLicenceType());
            //custPst.setString(11, cust.getDriverLicense().getLicenceNumber());
            //custPst.setDate(12, cust.getDriverLicense().getExpirationDate());
            
            if(custPst.toString().contains("?")){
                System.err.println("Statement still contains a ? and can't be executed");
            } else{
                	custPst.executeUpdate();
            }
            
            // Getting rid of the adders table = unnecessary 
            //addDAO.addAddress(cust.getHomeAddress(), cust.getCustomerId());
            //addDAO.addAddress(cust.getBillingAddress(), cust.getCustomerId());

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
	public Customer getCustomerbyID(int customerId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try {//Get Customer
	    	st = con.createStatement();
	    	String selectCustomerQuery = "SELECT * FROM Customers WHERE id = " + customerId + ";";
	    	ResultSet custRS = null;
	    	
	    	try {
	    		custRS = st.executeQuery(selectCustomerQuery);
	    	}catch(SQLException ex){
	    		System.err.print(ex);
	    	}
	    	
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	    	//Get Customer
	    	Customer customer = new Customer();
	    	License license = new License();
	    	Phone phone = new Phone();
    	  
	    	while ( custRS.next() ) {														// Customer Info Gathered
	    		customer.setCustomerId(custRS.getInt("ID"));								// ID
	    	  	customer.setFirstName(custRS.getString("firstName"));						// First Name
	    	  	customer.setLastName(custRS.getString("lastName"));						// Last Name
	    	  	/*
	    	  	customer.setDateOfBirth(custRS.getDate("dateOfBirth"));						// Billing Address
	    	  	phone.setNumber(custRS.getString("phoneNumber"));							// Phone Number
	    	  	phone.setPhoneType(custRS.getString("phoneType"));							// Phone Type
	    	  	customer.setPhone(phone);
	    	  	customer.setEmail(custRS.getString("email"));								// Email Address
	    	  	license.setLicenseType(custRS.getString("driverLicenseType"));				// Driver License Type/Classes
	    	  	license.setLicenseNumber(custRS.getString("licenseNumber"));				// License Number
	    	  	license.setExpirationDate(custRS.getDate("licenseExpirattionDate"));		// License Expiration Date
	    	  	customer.setDriverLicense(license);
	    	  	*/
	    	}
	      
	    	custRS.close();
	    	//customer.setHomeAddress(addDAO.getAddress(custRS.getInt("homeAddressID")));
	    	//customer.setBillingAddress(addDAO.getAddress(custRS.getInt("billingAddressID")));
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
	
	public Customer getCustomerbyUser(String user) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try {//Get Customer
	    	st = con.createStatement();
	    	String selectCustomerQuery = "SELECT * FROM Customers WHERE uname ='" + user +"';";
	    	ResultSet custRS = null;
	    	
	    	try {
	    		custRS = st.executeQuery(selectCustomerQuery);
	    	}catch(SQLException ex){
	    		System.err.print(ex);
	    	}
	    	
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	    	Customer customer = new Customer();
	    	
	    	while ( custRS.next() ) {														// Customer Info Gathered
	    		customer.setCustomerId(custRS.getInt("ID"));								// ID
	    	  	customer.setFirstName(custRS.getString("firstname"));						// First Name
	    	  	customer.setLastName(custRS.getString("lastname"));							// Last Name
	    	  	customer.setUserName(custRS.getString("uname"));
	    	  	customer.setPassword(custRS.getString("pass"));
	    	  	
	    	}
	      
	    	custRS.close();
	    	System.out.print("GOT");
	    	//customer.setHomeAddress(addDAO.getAddress(custRS.getInt("homeAddressID")));
	    	//customer.setBillingAddress(addDAO.getAddress(custRS.getInt("billingAddressID")));
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
	public void editCustomer(Customer cust) {
        Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try {//Get Customer
	    	st = con.createStatement();
	    	String selectCustomerQuery = "SELECT * FROM Customers WHERE id = " + cust.getCustomerId() + ";";
	    	ResultSet custRS = null;
	    	
	    	try {
	    		custRS = st.executeQuery(selectCustomerQuery);
	    	}catch(SQLException ex){
	    		System.err.print(ex);
	    	}
	    	
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	    	Customer customer = new Customer();
	    	
	    	while ( custRS.next() ) {														// Customer Info Gathered
	    		customer.setCustomerId(custRS.getInt("ID"));								// ID
	    	  	customer.setFirstName(custRS.getString("firstname"));						// First Name
	    	  	customer.setLastName(custRS.getString("lastname"));							// Last Name
	    	  	customer.setUserName(custRS.getString("uname"));
	    	  	customer.setPassword(custRS.getString("pass"));
	    	  	
	    	}
	    	System.out.print(customer.getUserName() + customer.getPassword());
	    	cust.setPassword(customer.getPassword());
	    	cust.setUserName(customer.getUserName());
	    	custRS.close();
	    	st.close();
	    }	    
	    catch (SQLException se) {
	    	System.err.println("CustomerDAO: Threw a SQLException retrieving the customer object.");
	    	System.err.println(se.getMessage());
	    	se.printStackTrace();
	    } finally {
	    	int customerId = cust.getCustomerId();
	        deleteCustomer(customerId);
	        addCustomer(cust);
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
	
	// Delete
	public void deleteCustomer(int customerId) {
        Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	String custDELStm = "DELETE FROM Customers WHERE ID = " + customerId + ";";
        	st.executeQuery(custDELStm);
        } catch (SQLException ex) {
        	
        } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      	System.err.println("CustomerDAO: Threw a SQLException deleting the customer object.");
      	      	System.err.println(ex.getMessage());
            }
        }
	}
        
}