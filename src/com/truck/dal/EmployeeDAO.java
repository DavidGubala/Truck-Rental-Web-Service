package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.user.Address;
import com.truck.user.Benefits;
import com.truck.user.Employee;
import com.truck.user.Partner;
import com.truck.user.Phone;

public class EmployeeDAO {
	/*
	 * Under Construction
	 * */
	// Create
	public void addEmployee(Employee emp) {
		Connection con = DBHelper.getConnection();
        PreparedStatement empPst = null;
        PreparedStatement billAddPst = null;
        PreparedStatement homeAddPST = null;

        try {
        	//Insert the empomer object
            String empStm = "INSERT INTO Employee(ID, firstName, lastName, dateOfBirth, homeAddressID, billingAddressID, phoneNumber, phoneType, email, employmentType, ssn, maritalStatus, benefitsId ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            empPst = con.prepareStatement(empStm);
            empPst.setInt(1, emp.getEmployeeId());
            empPst.setString(2, emp.getFirstName());
            empPst.setString(3, emp.getLastName());
            empPst.setDate(4, emp.getDateOfBirth());
            empPst.setInt(5, emp.getHomeAddress().getAddressId());
            empPst.setInt(6, emp.getBillingAddress().getAddressId());
            empPst.setString(7, emp.getPhone().getNumber());
            empPst.setString(8, emp.getPhone().getPhoneType());
            empPst.setString(9, emp.getEmail()); 
            empPst.setString(10, emp.getEmploymentType());
            empPst.setInt(11, emp.getSsn());
            empPst.setString(12, emp.getMaritalStatus());
            empPst.setInt(13, emp.getBenefits().getBenefitId());
            empPst.executeUpdate();
            
          //Insert the eployee Billing Address object
            String billAddStm = "INSERT INTO Address(employeeID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            billAddPst = con.prepareStatement(billAddStm);
            billAddPst.setInt(1, emp.getEmployeeId());
            billAddPst.setInt(2, emp.getBillingAddress().getAddressId());  
            billAddPst.setString(3, emp.getBillingAddress().getStreet());       
            billAddPst.setString(4, emp.getBillingAddress().getUnit());  
            billAddPst.setString(5, emp.getBillingAddress().getCity());  
            billAddPst.setString(6, emp.getBillingAddress().getState());      
            billAddPst.setString(7, emp.getBillingAddress().getZip());  
            billAddPst.executeUpdate();
            
          //Insert the employee Home Address object
            String homeAddStm = "INSERT INTO Address(employeeID, addressID, street, unit, city, state, zip) VALUES(?, ?, ?, ?, ?, ?, ?)";
            homeAddPST = con.prepareStatement(homeAddStm);
            homeAddPST.setInt(1, emp.getEmployeeId());
            homeAddPST.setInt(2, emp.getHomeAddress().getAddressId());  
            homeAddPST.setString(3, emp.getHomeAddress().getStreet());       
            homeAddPST.setString(4, emp.getHomeAddress().getUnit());
            homeAddPST.setString(5, emp.getHomeAddress().getCity());  
            homeAddPST.setString(6, emp.getHomeAddress().getState());      
            homeAddPST.setString(7, emp.getHomeAddress().getZip());
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
				if (empPst != null) {
					empPst.close();
				}
				if (con != null) {
				    con.close();
				}

            } catch (SQLException ex) {
      	      System.err.println("EmployeeDAO: Threw a SQLException saving the empomer object.");
    	      System.err.println(ex.getMessage());
            }
        }
    }
	// Read
	public Employee getEmployee(String employeeId) {
		 	 
	    try { 		
	    	// Get Employee via Employee ID
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectempomerQuery = "SELECT * FROM Employee WHERE ID = '" + employeeId + "'";

	    	ResultSet empRS = st.executeQuery(selectempomerQuery);      
	    	System.out.println("EmployeeDAO: *************** Query " + selectempomerQuery);
	    	
	      //Get Employee
    	  Employee employee = new Employee();
    	  Address homeAdd = new Address();
    	  Address billAdd = new Address();
    	  Phone phone = new Phone();
    	  Benefits benefits = new Benefits();
    	  
	      while ( empRS.next() ) {														// Employee Info Gathered
	    	  employee.setEmployeeId(empRS.getInt("ID"));								// ID
	    	  employee.setLastName(empRS.getString("firstName"));						// First Name
	    	  employee.setFirstName(empRS.getString("lastName"));						// Last Name
	    	  employee.setDateOfBirth(empRS.getDate("dateOfBirth"));					// DOB
	    	  homeAdd.setAddressId(empRS.getInt("homeAddressID"));						// Home Address
	    	  billAdd.setAddressId(empRS.getInt("billingAddressID"));					// Billing Address
	    	  phone.setNumber(empRS.getString("phoneNumber"));							// Phone Number
	    	  phone.setPhoneType(empRS.getString("phoneType"));							// Phone Type
	    	  employee.setPhone(phone);
	    	  employee.setEmail("email");												// Email Address
	    	  employee.setSsn(empRS.getInt("ssn"));									// Social Security Number
	    	  employee.setMaritalStatuse(empRS.getString("maritalStatus"));				// Marital Status
	    	  benefits.setBenefitId(empRS.getInt("benefitsId"));						// Benefits ID
	      }
	      //close to manage resources
	      empRS.close();
	      	    		  
	      //Get Home Address
	      String selectHomeAddressQuery = "SELECT * FROM Address WHERE ID = '" + employee.getHomeAddress().getAddressId() + "'";
	      ResultSet homeAddRS = st.executeQuery(selectHomeAddressQuery);
    	  
    	  System.out.println("empomerDAO: *************** Query " + selectHomeAddressQuery);
    	  
	      while ( homeAddRS.next() ) {								// Home Address Info Gathered
	    	  homeAdd.setStreet(homeAddRS.getString("street"));		// Street
	    	  homeAdd.setUnit(homeAddRS.getString("unit"));			// Unit
	    	  homeAdd.setCity(homeAddRS.getString("city"));			// City
	    	  homeAdd.setState(homeAddRS.getString("state"));		// State
	    	  homeAdd.setZip(homeAddRS.getString("zip"));			// postal code
	      }
	      
	      employee.setHomeAddress(homeAdd);
	      //close to manage resources
	      homeAddRS.close();
	      
	      //Get Bill Address
	      String selectBillAddressQuery = "SELECT * FROM Address WHERE ID = '" + employee.getBillingAddress().getAddressId() + "'";
	      ResultSet billAddRS = st.executeQuery(selectBillAddressQuery);
    	  
    	  System.out.println("empomerDAO: *************** Query " + selectBillAddressQuery);
    	  
	      while ( billAddRS.next() ) {								// Bill Address Info Gathered
	    	  billAdd.setStreet(billAddRS.getString("street"));		// Street
	    	  billAdd.setUnit(billAddRS.getString("unit"));			// Unit
	    	  billAdd.setCity(billAddRS.getString("city"));			// City
	    	  billAdd.setState(billAddRS.getString("state"));		// State
	    	  billAdd.setZip(billAddRS.getString("zip"));			// postal code
	      }
	      
	      employee.setBillingAddress(billAdd);
	      //close to manage resources
	      billAddRS.close();
	      
	      //Get Benefits Info 
	      String selectBenefitsQuery = "SELECT * FROM Benefits WHERE ID = '" + benefits.getBenefitId() + "'";
	      ResultSet benefitsRS = st.executeQuery(selectBenefitsQuery);
    	  
    	  System.out.println("empomerDAO: *************** Query " + selectBenefitsQuery);
    	  
	      while ( benefitsRS.next() ) {
	    	  benefits.setDental(benefitsRS.getBoolean("dental"));
	    	  benefits.setMedical(benefitsRS.getBoolean("medical"));
	      }
	      
	      employee.setBenefits(benefits);
	      //close to manage resources
	      benefitsRS.close();
	      
	      st.close();
	      
	      return employee;
	    }	    
	    catch (SQLException se) {
	      System.err.println("empomerDAO: Threw a SQLException retrieving the empomer object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	// Update
	public void EditPartner(Partner par) {
		
	}	
	// Delete
	public void deletePartner(Partner par) {
		
	}
}
