package com.truck.user;

import java.util.Random;

import com.truck.dal.*;

public class PartnerManager {
	
	private PartnerDAO partDAO = new PartnerDAO();
	
	public int addPartner(Partner partner) {
		Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(100000);
		
		try {
			partner.setPartnerId(id);
			partDAO.addPartner(partner);
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception creating partner.");
	      System.err.println(se.getMessage());
	    }

		return id;
	}
	
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
	
	public void editPartner(Partner partner) {
		try {
			partDAO.editPartner(partner);
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception editing partner.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public void deletePartner(int partnerId) {
		try {
			partDAO.deletePartner(partnerId);
	    } catch (Exception se) {
	      System.err.println("PartnerManager: Threw a Exception deleting customer.");
	      System.err.println(se.getMessage());
	    }
	}
}
