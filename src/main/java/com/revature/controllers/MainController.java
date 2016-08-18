package com.revature.controllers;

import com.revature.beans.*;
import com.revature.service.BusinessDelegate;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private BusinessDelegate bd;
	
	private List<Object> clients = new Vector<Object>();
	private List<Product> products = new Vector<Product>();
	
	@RequestMapping(value="goHome.do", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="goClients.do", method=RequestMethod.GET)
	public String clients(HttpServletRequest request) {
		request.setAttribute("newClient", new Client());
		return "clients";
	}
	
	@RequestMapping(value="goProducts.do", method=RequestMethod.GET)
	public String products(HttpServletRequest request) {
		request.setAttribute("newProduct", new Product());
		return "products";
	}
	
	@RequestMapping(value="getAllClients.do", method=RequestMethod.GET,
			produces="application/json")
	public List<Object> getAllClients() {
		clients = bd.getAllClients();
		return clients;
	}
	
}
