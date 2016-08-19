package com.revature.controllers;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.revature.IMS.BusinessDelegate;
import com.revature.beans.Client;
import com.revature.beans.Product;

@Controller
public class MainController{
	
	@Autowired
	private ServletContext context;
	
	private BusinessDelegate bd = new BusinessDelegate();
	
	private List<Object> clients = new Vector<Object>();
	private List<Object> products = new Vector<Object>();
	private List<Object> states = new Vector<Object>();
	
	@RequestMapping(value="goHome.do", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="goClients.do", method=RequestMethod.GET)
	public String clients(HttpServletRequest request) {
		return "clients";
	}
	
	@RequestMapping(value="goProducts.do", method=RequestMethod.GET)
	public String products() {
		return "products";
	}
	
	@RequestMapping(value="getAllClients.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllClients() {
		clients = bd.getAllClients();
		return clients;
	}
	@RequestMapping(value="createProduct.do", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public void createNewProduct(@RequestBody Product product){
		bd.createProduct(product);
	}
	
	@RequestMapping(value="getAllStates.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllStates() {
		states = bd.getAllStates();
		return states;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="insertClient.do",
			consumes="application/json")
	@ResponseBody
	public void insertClient(@RequestBody Client client) {
		System.out.println(client.getAddress().getState().getStateId());
		//bd.insertClient(client);
	}

	
}
