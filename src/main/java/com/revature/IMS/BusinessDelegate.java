package com.revature.IMS;

import java.util.List;

import com.revature.beans.*;
import com.revature.database.DataLayer;

public class BusinessDelegate {
	
	private DataLayer dl = new DataLayer();
	
	public List<Object> selectState(String abbr) {
		return dl.selectRestricted(new State(), "abbreviation", abbr);
	}
	
	public List<Object> getAllStates() {
		return dl.selectAllRows(new State());
	}
	
	public List<Object> getAllClients() {
		return dl.selectAllRows(new Client());
	}

	public void createProduct(Product product) {
		dl.createRow(product);
		
	}
	
	public void insertClient(Client client) {
		dl.createRow(client);
	}
	
}
