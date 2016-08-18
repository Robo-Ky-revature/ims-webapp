package com.revature.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.revature.beans.*;
import com.revature.database.DataLayer;

@Service
public class BusinessDelegate {
	
	private DataLayer dl = new DataLayer();
	
	public List<Object> getAllClients() {
		return dl.selectAllRows(new Client());
	}
	
}
