package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.user.Benefits;
import com.truck.user.Employee;
import com.truck.user.Phone;

public class EmployeeDAO {
    AddressDAO addDAO = new AddressDAO();
	// Create
	public void addEmployee(Employee emp) {
		Connection con = DBHelper.getConnection();
        PreparedStatement empPst = null;

        try {
        	//Insert the employee object
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
            
            addDAO.addAddress(emp.getHomeAddress(), emp.getEmployeeId());
            addDAO.addAddress(emp.getBillingAddress(), emp.getEmployeeId());

        } catch (SQLException ex) {

        } finally {

            try {
				if (empPst != null) {
					empPst.close();
				}
				if (con != null) {
				    con.close();
				}

            } catch (SQLException ex) {
      	      System.err.println("EmployeeDAO: Threw a SQLException saving the Employee object.");
    	      System.err.println(ex.getMessage());
            }
        }
    }
	// Read
	public Employee getEmployee(String employeeId) {

		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
	    	// Get Employee via Employee ID
	    	st = con.createStatement();
	    	String selectempomerQuery = "SELECT * FROM Employee WHERE ID = '" + employeeId + "'";

	    	ResultSet empRS = st.executeQuery(selectempomerQuery);      
	    	System.out.println("EmployeeDAO: *************** Query " + selectempomerQuery);
	    	
	      //Get Employee
    	  Employee employee = new Employee();
    	  Phone phone = new Phone();
    	  Benefits benefits = new Benefits();
    	  
	      while ( empRS.next() ) {														// Employee Info Gathered
	    	  employee.setEmployeeId(empRS.getInt("ID"));								// ID
	    	  employee.setLastName(empRS.getString("firstName"));						// First Name
	    	  employee.setFirstName(empRS.getString("lastName"));						// Last Name
	    	  employee.setDateOfBirth(empRS.getDate("dateOfBirth"));					// DOB
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
	      employee.setHomeAddress(addDAO.getAddress(empRS.getInt("homeAddressID")));
	      employee.setBillingAddress(addDAO.getAddress(empRS.getInt("billingAddressID")));
	      
	      //Get Benefits Info 
	      String selectBenefitsQuery = "SELECT * FROM Benefits WHERE ID = '" + benefits.getBenefitId() + "'";
	      ResultSet benefitsRS = st.executeQuery(selectBenefitsQuery);
    	  
    	  System.out.println("empomerDAO: *************** Query " + selectBenefitsQuery);
    	  
	      while ( benefitsRS.next() ) {
	    	  benefits.setDental(benefitsRS.getBoolean("dental"));
	    	  benefits.setMedical(benefitsRS.getBoolean("medical"));
	      }
	      
	      employee.setBenefits(benefits);
	      benefitsRS.close();
	      
	      st.close();
	      
	      return employee;
	    }	    
	    catch (SQLException se) {
	      System.err.println("EmplyeeDAO: Threw a SQLException retrieving the employee object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("EmployeeDAO: Threw a SQLException saving the Employee object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	  }
	// Update
	public void EditPartner(Employee emp) {
		int empId = emp.getEmployeeId();
		deleteEmployee(empId);
		addEmployee(emp);
	}	
	// Delete
	public void deleteEmployee(int employeeId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
        try {
        	st = con.createStatement();
        	
            String empDELStm = "DELETE * FROM Customer WHERE ID = '" + employeeId + "'";
            st.executeQuery(empDELStm);
            
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
