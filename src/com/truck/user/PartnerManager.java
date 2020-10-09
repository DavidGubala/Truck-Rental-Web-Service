package com.truck.user;

import com.truck.dal.*;

public class PartnerManager {
	
	private PartnerDAO partDAO = new PartnerDAO();
	
	// Using CRUD
	
	//Create
	public void addPartner(Partner partner) {
		try {
			partDAO.addPartner(partner);
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception creating partner.");
	      System.err.println(se.getMessage());
	    }
	}
	//Read
	public Partner getPartner(int partnerId) {
		try {
			Partner partner = partDAO.getPartner(partnerId);
	    	return partner;
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	//update
	public void editPartner(Partner partner) {
		try {
			partDAO.editPartner(partner);
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception editing partner.");
	      System.err.println(se.getMessage());
	    }
	}
	//Delete
	public void deletePartner(int partnerId) {
		try {
			partDAO.deletePartner(partnerId);
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception deleting customer.");
	      System.err.println(se.getMessage());
	    }
	}
}
