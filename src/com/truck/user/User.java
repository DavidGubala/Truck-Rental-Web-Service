package com.truck.user;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private String firstName;
	private String lastName;
	private String CompanyName;
	private Address homeAddress;
	private Address billingAddress;
	private Phone phone;
	private String email;
	private Date dateOfBirth;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String CompanyName) {
		this.CompanyName = CompanyName;
	}
	
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dob) {
		this.dateOfBirth = dob;
	}
}
