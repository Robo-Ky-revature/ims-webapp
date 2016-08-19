package com.revature.IMS;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.*;
import com.revature.database.DataLayer;

public class BusinessDelegate {
	
	private static Logger log = Logger.getRootLogger();
	
	private DataLayer dl = new DataLayer();
	
	public List<Object> selectState(String stateId) {
		return dl.selectRestricted(new State(), "stateId", Integer.parseInt(stateId));
	}
	
	public List<Object> selectType(String clientId) {
		return dl.selectRestricted(new ClientType(), "clientId", Integer.parseInt(clientId));
	}

	public List<Object> getAllProducts() {
		return dl.selectAllRows(new Product());
	}
	
	public List<Object> getAllStates() {
		return dl.selectAllRows(new State());
	}
	
	public List<Object> getAllClients() {
		return dl.selectAllRows(new Client());
	}
	
	public void insertAddress(Address address) {
		dl.createRow(address);
	}

	public void createProduct(Product product) {
		dl.createRow(product);
		
	}
	
	public void insertClient(Client client) {
		dl.createRow(client);
	}
	
}
