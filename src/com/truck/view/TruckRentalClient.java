package com.truck.view;

import java.util.List;

import com.truck.model.customer.Address;
import com.truck.model.customer.Customer;
import com.truck.model.item.Truck;
import com.truck.model.order.Order;
import com.truck.model.order.OrderDetail;

public class TruckRentalClient {
			
		public static void main (String args[]) throws Exception {
						
	        Customer customer = new Customer();;
			customer.setFirstName("Michael");
	        customer.setLastName("Gerard");
	        customer.setCustomerId("AY2345");
	        Address billingAddress = new Address();
	        billingAddress.setStreet("500 West Madison St.");
	        billingAddress.setUnit("Suite 1000");
	        billingAddress.setCity("Chicago");
	        billingAddress.setState("IL");
	        billingAddress.setZip("66610");
	        customer.setBillingAddress(billingAddress);

	        //Create order for the customer
	        Order order1 = new Order();
	        order1.setOrderID("BO-66734");

	        //Associate the order with the customer
	        customer.addOrder(order1);
	        
	        //order detail contains products ordered
	        //First product
	        Truck product1 = new Truck();
	        product1.setID("BF-7898");
	     
	        product1.setMake("FORD");
	        product1.setPlateNumber("IL2242");
	        product1.setTruckID("VAN4231");
	        product1.setYear("2018");
	        product1.setPricePerMile(10.00);
	        //Add product to order
	        order1.addProduct(product1, 1);
	        
	        //Second product
	        Truck product2 = new Truck();
	        product2.setMake("FORD");
	        product2.setPlateNumber("Web Application Architecture");
	        product2.setTruckID("TRU2242");
	        product2.setPricePerMile(15.00);
	        //Add the products detail to Order
	        order1.addProduct(product2, 1);
	        
	        //finish order	        
	        order1.confirmOrder();
	        order1.orderPayed();
	        
	        // NOTE: To cancel the request, un-comment the following line.
	        //order.cancel();
	        
	        // Then, comment out the next 2 lines.
	       // order1.orderSendOut();
	       // order1.orderDelivered();
	        
	        // Print out an order summary
	        Address billingAdd = customer.getBillingAddress();
	        List<OrderDetail> orderLines = order1.getOrderDetails();
	        double orderTotal = order1.getOrderTotal();

	        // Format order output
	        System.out.println("\tOrder Id: \t\t" + order1.getOrderID() + "\n");
	        System.out.println("\tOrder status: \t\t" + order1.getOrderState() + "\n");
	        System.out.println("\tName: \t\t\t" + customer.getFirstName() + " " + customer.getLastName() + "\n");

	        System.out.println("\tBilling Address:\t" + billingAdd.getStreet() + 
	        		"\n\t\t\t\t" + billingAdd.getUnit() + 
	        		"\n\t\t\t\t" + billingAdd.getCity() + ", " + 
	        		billingAdd.getState() + " " + billingAdd.getZip() +
	        		"\n");

	        System.out.println("\tOrder Items: ");
	        for (OrderDetail line : orderLines) {
	        	System.out.println("\t\t\t\t" + line.getProduct().getID() + "\t" + 
	        			line.getProduct().getPricePerMile() + " x " + line.getQuantity());
	        }

	        System.out.println("\n\tOrder Total:\t\t" + orderTotal);
	        
		}

}