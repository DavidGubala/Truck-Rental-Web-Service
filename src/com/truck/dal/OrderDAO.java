package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.order.Order;
import com.truck.order.Reservation;
import com.truck.order.Transaction;
import com.truck.product.Vehicle;
import com.truck.user.Customer;

public class OrderDAO {
	CustomerDAO custDAO = new CustomerDAO();
	VehicleDAO vehDAO = new VehicleDAO();
	//TransactionDAO transDAO = new TransactionDAO(); // these DAO haven't been implemented yet.
	//REservationDAO resDAO = new ReservationDAO();
	
	// Create
	public void createOrder(Order ord) {
		Connection con = DBHelper.getConnection();
        PreparedStatement ordPst = null;

        try {
        	//Insert the order object
            String ordStm = "INSERT INTO Orders(ID, prducuctID, customerID, orderDate, orderStatus, reservationID, transactionID, ) VALUES(?, ?, ?, ?, ?, ?, ?)";
            ordPst = con.prepareStatement(ordStm);
            ordPst.setInt(1, ord.getOrderId());
            ordPst.setInt(2, ord.getVehicle().getProductId());
            ordPst.setInt(3, ord.getCustomer().getCustomerId());
            ordPst.setDate(4, ord.getDate());
            ordPst.setString(5, ord.getOrderStatus());
            ordPst.setInt(6, ord.getReservation().getReservationId());
            ordPst.setInt(7, ord.getTransaction().getTrasactionId());
            
            if(ordPst.toString().contains("?")){
                System.err.println("Statement still contains a ? and can't be executed");
            } else{
            	ordPst.executeUpdate();
            }
            
            //Need to add Reservation and Transaction DAO.create here
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (ordPst != null) {
                	ordPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("orderDAO: Threw a SQLException saving the order object.");
    	      System.err.println(ex.getMessage());
            }
        }
	}
	// Read
	public Order getOrder(int ordId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try { 		
	    	//Get Order
	    	st = con.createStatement();
	    	String selectOrderQuery = "SELECT * FROM Orders WHERE ID = '" + ordId + "'"; 

	    	ResultSet ordRS = st.executeQuery(selectOrderQuery);      
	    	System.out.println("OrderDAO: *************** Query " + selectOrderQuery);
	    	
	    	Order order = new Order();
	    	//Reservation reservation = new Reservation();
	    	//Transaction transaction = new Transaction();
	    	Customer customer = new Customer();
	    	Vehicle vehicle = new Vehicle();
	    	
	    	while ( ordRS.next() ) {
	    		
	    		order.setOrderId(ordRS.getInt("orderID"));
	    		order.setDate(ordRS.getDate("orderDate"));
	    		order.setOrderStatus(ordRS.getNString("orderStatus"));
	    		
	    		customer = custDAO.getCustomer(ordRS.getInt("customerID"));
	    		vehicle = vehDAO.getVehicle(ordRS.getInt("prducuctID"));
	    		//reservation = resDAO.getReservation(ordRS.getInt("reservationID"));
	    		//transaction = transDAO.getTransaction(ordRS.getInt("transactionID"));
	    		
	    		
	    		order.setCostumer(customer);
	    		order.setVehicle(vehicle);
	    		//order.setReservation(reservation);
	    		//order.setTransaction(transaction);
	    	}
	    	
	    	ordRS.close();
	    	st.close();
	    	
	    	return order;
	    }	    
	    catch (SQLException se) {
	      System.err.println("OrderDAO: Threw a SQLException retrieving the order object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("OrderDAO: Threw a SQLException saving the order object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	}
	
	//Update
	public void editOrder(Order order) {
		int orderId = order.getOrderId();
		deleteOrder(orderId);
		createOrder(order);
	}
	
	//Delete
	public void deleteOrder(int orderId) {
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
		try {
			st = con.createStatement();
			String orderDELStm = "DELETE FROM Orders WHERE ID=" + orderId + ";";
			st.executeQuery(orderDELStm);
		} catch (SQLException ex) {
			
		} finally {
			
			try {
				if(st != null) {
					st.close();
				}
			} catch (SQLException ex) {
				System.err.println("OrderDAO: Threw a SQLException deleting the order object.");
				System.err.println(ex.getMessage());
			}
		}
	}
}
