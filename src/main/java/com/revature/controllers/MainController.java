package com.revature.controllers;

import com.revature.beans.*;
import java.util.*;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@Autowired
	private ServletContext context;
	
	private List<Client> clients = new Vector<Client>();
	private List<Product> products = new Vector<Product>();
	
	@RequestMapping(value="goHome.do", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="goClients.do", method=RequestMethod.GET)
	public String clients() {
		return "clients";
	}
	
	@RequestMapping(value="goProducts.do", method=RequestMethod.GET)
	public String products() {
		return "products";
	}
	
}
