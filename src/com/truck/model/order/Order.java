package com.truck.model.order;

<<<<<<< HEAD
import com.truck.model.product.Product;
import com.truck.model.user.Customer;
import com.truck.model.order.Reservation;
import java.time.LocalDateTime;

public class Order {
	private int orderId;
	private LocalDateTime orderDate;
	private String orderStatus;
	
	private Customer customer;
	private Product product;
	private Reservation reservaton;
	private Transaction transaction;
	
	public int getOrderId() {
		return orderId;
	}
=======
import java.util.ArrayList;
import java.util.List;
import com.truck.model.item.Product;

public class Order {
	private String orderID;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	private Product product;
	private String date;        //When did they order
	private String duration;    //How long they will rent
	//private int quantity;
	private String orderState = "Open";
	private boolean paymentReceived;
>>>>>>> 5ad29b0b4789bcaaa72ba6576db4d64204aad9ac

	public void setOrderId(int id) {
		this.orderId = id;
	}
	
<<<<<<< HEAD
	public LocalDateTime getDate() {
		return orderDate;
	}
	
	public void setDate(LocalDateTime date) {
		this.orderDate = date;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String status) {
		this.orderStatus = status;
=======
//	public OrderDetail(Product product, int quantity) {
//		this.product = product;
//		this.quantity = quantity;
//	}
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderId) {
		this.orderID = orderId;
>>>>>>> 5ad29b0b4789bcaaa72ba6576db4d64204aad9ac
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCostumer(Customer customer) {
		this.customer = customer;
	}

	public Reservation getReservation() {
		return reservaton;
	}
	
	public void setReservation(Reservation reservaton) {
		this.reservaton = reservaton;
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
<<<<<<< HEAD
}
=======
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getOrderState() {
		return orderState;
	}
	
	public boolean isPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	
	public void addProduct(Product product, int quantity) {
		if (orderState.equals("Open")) {
			orderDetails.add(new OrderDetail(product, quantity));
		} else {
			throw new IllegalStateException("Can only add product in Open state.");
		}
	}
	
	public void cancelOrder() {
		if (orderState.equals("Open") || orderState.equals("Ordered")) {
			orderState = "Canceled";
		} else {
			throw new IllegalStateException("Cannot cancel order in this state.");
		}
	}
	
	public void confirmOrder() {
		if (getOrderDetails().isEmpty()) {
			orderState = "Canceled";
		} else if (orderState.equals("Open")) {
			orderState = "Ordered";
		} else {
			throw new IllegalStateException("Cannot confirm order in this state.");
		}
	}
	
	
	public void orderPayed() {
		if (orderState.equals("Ordered")) {
			setPaymentReceived(true);
		} else {
			throw new IllegalStateException("Cannot pay in this state.");
		}
	}
	
	
	public boolean isFinished() {
		if (orderState.equals("Delivered") || orderState.equals("Canceled")) {
			return true;
		}
		return false;
	}
	
	public double getOrderTotal() {
		double total = 0.00;
		for (OrderDetail line : orderDetails) {
			total += line.getProduct().getPricePerMile() * line.getQuantity();
		}
		return total;
	}

}
	
//	public int getQuantity() {
//		return quantity;
//	}
//	
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}


>>>>>>> 5ad29b0b4789bcaaa72ba6576db4d64204aad9ac
