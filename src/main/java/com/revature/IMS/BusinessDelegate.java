package com.revature.IMS;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
		return dl.selectAllRows(new Product(), "productName", true);
	}
	
	public List<Object> getAllStates() {
		return dl.selectAllRows(new State(), "stateId", true);
	}
	
	public List<Object> getAllClients() {
		return dl.selectAllRows(new Client(), "name", true);
	}
	
	public List<Object> getAllInvoices() {
		return dl.selectAllRows(new PurchaseOrder(), "orderNumber", false);
	}
	
	public List<Object> getAllPoLines() {
		return dl.selectAllRows(new POLine(), "poLineId.order", true);
	}
	
	public List<Object> getPoLines(int orderNumber) {
		return dl.selectRestricted(new POLine(), "order", orderNumber);
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
	public void updateCategory(Category category){
		dl.updateRow(category);
	}
	public void deleteClient(Serializable id) {
		dl.delete(new Client(), id);
	}

	public List<Object> getAllCategories() {
		return dl.selectAllRows(new Category(),"catagoryId",true);
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

	public void updateProduct(Product product) {
		dl.updateRow(product);
		
	}

	
}
