package com.revature.IMS;

import java.io.Serializable;
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
	
	public List<Object> selectClient(String clientId) {
		return dl.selectRestricted(new Client(), "clientId", Integer.parseInt(clientId));
	}
	
	public List<Object> selectProduct(String upc) {
		return dl.selectRestricted(new Product(), "upc", Integer.parseInt(upc));
	}

	public List<Object> getAllProducts() {
		return dl.selectAllRows(new Product(), "productName");
	}
	
	public List<Object> getAllStates() {
		return dl.selectAllRows(new State(), "stateId");
	}
	
	public List<Object> getAllClients() {
		return dl.selectAllRows(new Client(), "name");
	}
	
	public void insertAddress(Address address) {
		dl.createRow(address);
	}
	
	public void updateAddress(Address address) {
		dl.updateRow(address);
	}

	public void createProduct(Product product) {
		dl.createRow(product);
	}
	public Category getCategory(Serializable id){
		return dl.getCategory(id);
	}
	
	public void insertClient(Client client) {
		dl.createRow(client);
	}
	
	public void updateClient(Client client) {
		dl.updateRow(client);
	}
	
	public void deleteClient(Serializable id) {
		dl.delete(new Client(), id);
	}

	public List<Object> getAllCategories() {
		return dl.selectAllRows(new Category(),"catagoryId");
	}

	public void deleteProduct(Serializable id) {
		dl.delete(new Product(), id);
	}
	
	public void insertPoLine(POLine line) {
		dl.createRow(line);
	}
	
	public void insertPurchaseOrder(PurchaseOrder order) {
		dl.createRow(order);
	}

	
}
