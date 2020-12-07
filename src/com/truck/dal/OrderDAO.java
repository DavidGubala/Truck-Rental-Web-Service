package com.truck.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.truck.domain.model.order.Order;

public class OrderDAO {
	CustomerDAO custDAO = new CustomerDAO();
	VehicleDAO vehDAO = new VehicleDAO();
	//TransactionDAO transDAO = new TransactionDAO(); // these DAO haven't been implemented yet.
	//ReservationDAO resDAO = new ReservationDAO();
	
	// Create
	public void createOrder(Order ord) {
		Connection con = DBHelper.getConnection();
        PreparedStatement ordPst = null;

        try {
        	//Insert the order object
            String ordStm = "INSERT INTO Orders(ID, vehicleID, partnerId, customerID, orderStatus) VALUES(?, ?, ?, ?, ?)";
            ordPst = con.prepareStatement(ordStm);
            ordPst.setInt(1, ord.getOrderId());
            ordPst.setInt(2, ord.getVehicleId());
            ordPst.setInt(3,  ord.getPartnerId());
            ordPst.setInt(4, ord.getCustomerId());
            ordPst.setString(5, ord.getOrderStatus());
            //ordPst.setInt(6, ord.getReservation().getReservationId());
            //ordPst.setInt(7, ord.getTransaction().getTrasactionId());
            
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
	    	//Customer customer = new Customer();
	    	//Vehicle vehicle = new Vehicle();
	    	
	    	while ( ordRS.next() ) {
	    		order.setOrderId(ordRS.getInt("id"));
	    		//order.setDate(ordRS.getDate("orderDate"));
	    		order.setOrderStatus(ordRS.getString("orderStatus"));
	    		order.setCustomerId(ordRS.getInt("customerId"));
	    		order.setVehicleId(ordRS.getInt("vehicleid"));
	    		order.setVehicleId(ordRS.getInt("vehicleId"));
	    		order.setPartnerId(ordRS.getInt("partnerId"));
	    		
	    		//customer = custDAO.getCustomer(ordRS.getInt("customerID"));
	    		//vehicle = vehDAO.getVehicle(ordRS.getInt("prducuctID"));
	    		//reservation = resDAO.getReservation(ordRS.getInt("reservationID"));
	    		//transaction = transDAO.getTransaction(ordRS.getInt("transactionID"));
	    		
	    		
	    		//order.setCostumer(customer);
	    		//order.setVehicle(vehicle);
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
	
	public List<Order> getPartnerOrders(int partnerId){
		List<Order> orders = new ArrayList<Order>();
		
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try {
	    	st = con.createStatement();
	    	String selectOrderQuery = "SELECT * FROM Orders WHERE PartnerID = " + partnerId + ";";
	    	
	    	ResultSet ordRS = st.executeQuery(selectOrderQuery);      
	    	System.out.println("OrderDAO: *************** Query " + selectOrderQuery);
	    	
    	  Order order;
    	  //Customer customer = new Customer();
    	  //Vehicle vehicle = new Vehicle();
	      while (ordRS.next() ) {
	    	  order = new Order();
	    	  order.setOrderId(ordRS.getInt("id"));
	    	  //order.setDate(ordRS.getDate("orderDate"));
	    	  order.setOrderStatus(ordRS.getString("orderStatus"));
	    	  order.setCustomerId(ordRS.getInt("customerId"));
	    	  order.setVehicleId(ordRS.getInt("vehicleId"));
	    	  order.setPartnerId(ordRS.getInt("partnerId"));
	    	  
	    	  //customer = custDAO.getCustomer(ordRS.getInt("customerID"));
	    	  //vehicle = vehDAO.getVehicle(ordRS.getInt("prducuctID"));
	    	  //order.setCostumer(customer);
	    	  //order.setVehicle(vehicle);
	    	  
	    	  orders.add(order);
	      }
	      
	      ordRS.close();
	      st.close();
	      
	      return orders;
	    }	    
	    catch (SQLException se) {
	      System.err.println("VehicleDAO: Threw a SQLException retrieving the vehicle object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("VehicleDAO: Threw a SQLException saving the vehicle object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	}
	
	public List<Order> getCustomerOrders(int customerId){
		List<Order> orders = new ArrayList<Order>();
		
		Connection con = DBHelper.getConnection();
		Statement st = null;
		
	    try {
	    	st = con.createStatement();
	    	String selectOrderQuery = "SELECT * FROM Orders WHERE CustomerID = " + customerId + ";";
	    	
	    	ResultSet ordRS = st.executeQuery(selectOrderQuery);      
	    	System.out.println("OrderDAO: *************** Query " + selectOrderQuery);
	    	
    	  Order order;
    	  //Customer customer = new Customer();
    	  //Vehicle vehicle = new Vehicle();
	      while (ordRS.next() ) {
	    	  order = new Order();
	    	  order.setOrderId(ordRS.getInt("id"));
	    	  //order.setDate(ordRS.getDate("orderDate"));
	    	  order.setOrderStatus(ordRS.getString("orderStatus"));
	    	  order.setCustomerId(ordRS.getInt("customerId"));
	    	  order.setPartnerId(ordRS.getInt("partnerId"));
	    	  order.setVehicleId(ordRS.getInt("vehicleId"));
	    	  
	    	  //customer = custDAO.getCustomer(ordRS.getInt("customerID"));
	    	  //vehicle = vehDAO.getVehicle(ordRS.getInt("prducuctID"));
	    	  //order.setCostumer(customer);
	    	  //order.setVehicle(vehicle);
	    	  orders.add(order);
	      }
	      
	      ordRS.close();
	      st.close();
	      
	      return orders;
	    }	    
	    catch (SQLException se) {
	      System.err.println("VehicleDAO: Threw a SQLException retrieving the vehicle object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    } finally {

            try {
                if (st != null) {
                	st.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("VehicleDAO: Threw a SQLException saving the vehicle object.");
    	      System.err.println(ex.getMessage());
            }
	    }
	    return null;
	}
}
