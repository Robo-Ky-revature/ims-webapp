package com.revature.IMS;

import java.util.List;

import com.revature.beans.*;
import com.revature.database.DataLayer;

public class BusinessDelegate {
	
	private DataLayer dl = new DataLayer();
	
	public List<Object> getAllClients() {
		return dl.selectAllRows(new Client());
	}
	
}
