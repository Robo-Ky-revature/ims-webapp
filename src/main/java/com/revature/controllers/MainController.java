package com.revature.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.IMS.BusinessDelegate;
import com.revature.beans.Category;
import com.revature.beans.Client;
import com.revature.beans.Product;

@Controller
public class MainController implements ApplicationContextAware{
	private static Logger log = Logger.getRootLogger();
	@Autowired
	private ApplicationContext context;
	
	private BusinessDelegate bd = new BusinessDelegate();
	
	private List<Object> clients = new Vector<Object>();
	private List<Object> products = new Vector<Object>();
	
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
	@RequestMapping(value="createProduct.do", method=RequestMethod.POST)
	@ResponseBody
	public void createNewProduct(HttpServletRequest req){
		Set<Product> products = new HashSet<Product>();
		Set<Category> catagories = new HashSet<Category>();
		
		Product product = (Product) context.getBean("product");
		product.setProductName(req.getParameter("productName"));
		product.setShortName(req.getParameter("shortName"));
		product.setReorder(Integer.parseInt(req.getParameter("reorder")));log.error("reorder needs authentication");
		product.setWeight(Double.parseDouble(req.getParameter("weight")));log.error("weight needs authentication");
		product.setSize(req.getParameter("size"));
		product.setCost(Double.parseDouble(req.getParameter("cost")));log.error("unit cost needs authentication");
		product.setPrice(Double.parseDouble(req.getParameter("price")));log.error("sales cost needs authentication");
		product.setDescription(req.getParameter("description"));
		products.add(product);
		Category category = new Category(1, "test Case",products);
		catagories.add(category);
		product.setCatagories(catagories);
		log.debug("inserting new product " +product);
		bd.createProduct(product);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="insertClient.do",
			consumes="application/json")
	@ResponseBody
	public void insertClient(@RequestBody Client client) {
		bd.insertClient(client);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		
		this.context=context;
		
	}

	
}
