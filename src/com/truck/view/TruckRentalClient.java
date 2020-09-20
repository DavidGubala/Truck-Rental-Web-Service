package com.truck.view;

import java.util.List;

import com.truck.model.user.Address;
import com.truck.model.user.Customer;
import com.truck.model.order.Order;
import com.truck.model.order.OrderDetail;
import com.truck.model.product.Vehicle;


public class TruckRentalClient {
			
		public static void main (String args[]) throws Exception {
						
	        Customer customer = new Customer();
			customer.setFirstName("Michael");
	        customer.setLastName("Gerard");
	        customer.setDriverLicense("AY2345");
	        
	        Address billingAddress = new Address();
	        billingAddress.setStreet("500 West Madison St.");
	        billingAddress.setUnit("Suite 1000");
	        billingAddress.setCity("Chicago");
	        billingAddress.setState("IL");
	        billingAddress.setZip("66610");
	        customer.setBillingAddress(billingAddress);

	        //Create order for the customer
	        Order order1 = new Order();
	        order1.setOrderId(1);

	        //Associate the order with the customer
	        customer.addOrder(order1);
	        
	        //order detail contains products ordered
	        //First product
	        Vehicle product1 = new Vehicle();
	    
	        product1.setProductId(1);
	        product1.setMake("FORD");
	        product1.setModel("Raptor");
	        product1.setPlateNumber("IL2242");
	        product1.setType("Pick-Up");
	        product1.setYear(2018);
	        product1.setPricePerMile(10.00);
	        //Add product to order
	        order1.addProduct(product1, 1);
	        
	        //Second product
	        Vehicle product2 = new Vehicle();
	        product2.setProductId(2);
	        product2.setMake("FORD");
	        product2.setModel("Transit");
	        product2.setType("Van");
	        product2.setPlateNumber("IL7227");
	        product2.setPricePerMile(15.00);
	        //Add the products detail to Order
	        order1.addProduct(product2, 1);
	        
	        //finish order	        
	        order1.confirmOrder();
	        
	        // NOTE: To cancel the request, un-comment the following line.
	        //order.cancel();
	        
	        // Print out an order summary
	        Address billingAdd = customer.getBillingAddress();
	        List<OrderDetail> orderLines = order1.getOrderDetails();
	        double orderTotal = order1.getOrderTotal();

	        // Format order output
	        System.out.println("\tOrder Id: \t\t" + order1.getOrderId() + "\n");
	        System.out.println("\tOrder status: \t\t" + order1.getOrderStatus() + "\n");
	        System.out.println("\tName: \t\t\t" + customer.getFirstName() + " " + customer.getLastName() + "\n");

	        System.out.println("\tBilling Address:\t" + billingAdd.getStreet() + 
	        		"\n\t\t\t\t" + billingAdd.getUnit() + 
	        		"\n\t\t\t\t" + billingAdd.getCity() + ", " + 
	        		billingAdd.getState() + " " + billingAdd.getZip() +
	        		"\n");

	        System.out.println("\tOrder Items: ");
	        for (OrderDetail line : orderLines) {
	        	System.out.println("\t\t\t\t" + line.getProduct().getProductId() + "\t" + 
	        			line.getProduct().getPricePerMile() + " x " + line.getQuantity());
	        }

	        System.out.println("\n\tOrder Total:\t\t" + orderTotal);
	        
		}

}