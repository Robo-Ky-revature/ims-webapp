package com.revature.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.revature.IMS.BusinessDelegate;
import com.revature.beans.Address;
import com.revature.beans.Category;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.Product;
import com.revature.beans.State;

@Controller
public class MainController implements ApplicationContextAware{
	
	private static Logger log = Logger.getRootLogger();
	
	
	
	@Autowired
	private ApplicationContext context;
	
	private BusinessDelegate bd = new BusinessDelegate();
	
	private List<Object> clients = new Vector<Object>();
	private List<Object> products = new Vector<Object>();
	private List<Object> states = new Vector<Object>();
	private List<Object> catagories = new Vector<Object>();
	
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
	@RequestMapping(value="getAllProducts.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllProducts() {
		products = bd.getAllProducts();
		return products;
	}
	@RequestMapping(value="createProduct.do", method=RequestMethod.POST)
	public void createNewProduct(@ModelAttribute @Valid Product product,BindingResult bindingResult, HttpServletRequest req,
			HttpServletResponse resp){
//		if (bindingResult.hasErrors()){
//			return new ModelAndView("home");
//		}
		//BindingResult bindingResult=BindingResult
		log.info("starting product creation");
		Set<Product> products = new HashSet<Product>();
		Set<Category> catagories = new HashSet<Category>();
		
		product = (Product) context.getBean("product");
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
		log.info("inserting new product " +product);
		//ModelAndView mv = new ModelAndView();
		
		//mv.setViewName("view");
		//mv.addObject("success","Product added");
		bd.createProduct(product);
	//	return mv;
		
		
		
	}
	
	@RequestMapping(value="getAllStates.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllStates() {
		states = bd.getAllStates();
		return states;
	}
	@RequestMapping(value="getCategories.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllCategories() {
		catagories = (List<Object>) bd.getAllCategories();
		return catagories;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="insertClient.do")
	@ResponseBody
	public String insertClient(HttpServletRequest request) {
		ClientType type = (ClientType) bd.selectType(request.getParameter("type")).get(0);
		State st = (State) bd.selectState(request.getParameter("state")).get(0);
		Address add = new Address();
		add.setStreetAddress1(request.getParameter("streetAddress1"));
		add.setStreetAddress2(request.getParameter("streetAddress2"));
		add.setCity(request.getParameter("city"));
		add.setState(st);
		add.setZip(request.getParameter("zip"));
		bd.insertAddress(add);
		Client client = new Client();
		client.setAddress(add);
		client.setType(type);
		client.setName(request.getParameter("name"));
		client.setEmail(request.getParameter("email"));
		client.setPhone(request.getParameter("phone"));
		client.setFax(request.getParameter("fax"));
		client.setContactName("Grace");
		bd.insertClient(client);
		return "clients";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="updateClient.do")
	@ResponseBody
	public String updateClient(HttpServletRequest request) {
		//ClientType type = (ClientType) bd.selectType(request.getParameter("type")).get(0);
		State st = (State) bd.selectState(request.getParameter("state")).get(0);
		Address add = new Address();
		add.setStreetAddress1(request.getParameter("streetAddress1"));
		add.setStreetAddress2(request.getParameter("streetAddress2"));
		add.setCity(request.getParameter("city"));
		add.setState(st);
		add.setZip(request.getParameter("zip"));
		bd.updateAddress(add);
		Client client = new Client();
		client.setAddress(add);
		//client.setType(type);
		client.setClientId(Integer.parseInt(request.getParameter("clientId")));
		client.setName(request.getParameter("name"));
		client.setEmail(request.getParameter("email"));
		client.setPhone(request.getParameter("phone"));
		client.setFax(request.getParameter("fax"));
		client.setContactName("Grace");
		bd.updateClient(client);
		return "clients";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="deleteClient.do")
	@ResponseBody
	public String deleteClient(HttpServletRequest request) {
		bd.deleteClient(Integer.parseInt(request.getParameter("clientId")));
		return "clients";
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		
		this.context=context;
		
	}

	
}
