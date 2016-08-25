package com.revature.controllers;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.revature.IMS.BusinessDelegate;
import com.revature.beans.Address;
import com.revature.beans.Category;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.POLine;
import com.revature.beans.POLineId;
import com.revature.beans.Product;
import com.revature.beans.PurchaseOrder;
import com.revature.beans.State;

@Controller
public class MainController implements ApplicationContextAware{
	
	private static Logger log = Logger.getRootLogger();
	
	
	
	@Autowired
	private ApplicationContext context;
	
	private BusinessDelegate bd = new BusinessDelegate();
	
	private List<Object> clients = new Vector<Object>();
	private Set<Object> products = new HashSet<Object>();
	private List<Object> states = new Vector<Object>();
	private List<Object> catagories = new Vector<Object>();
	private List<Object> invoices = new Vector<Object>();
	private List<Object> polines = new Vector<Object>();
	
	@RequestMapping(value="goHome.do", method=RequestMethod.GET)
	public String home() {
		return "home"; 
	}
	
	@RequestMapping(value="goInvoices.do", method=RequestMethod.GET)
	public String invoices() {
		return "invoices";
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
	public Set<Object> getAllProducts() {
//		Set<Object> setProduct = new HashSet<Object>(bd.getAllProducts());
		products = new HashSet<Object>(bd.getAllProducts());

//		for (Object object : products) {
//			log.error(object.toString());
//		}
		return products;
	}
	@RequestMapping(value="createProduct.do", method=RequestMethod.POST)
	public ModelAndView createNewProduct(HttpServletRequest req){
		
//		if (bindingResult.hasErrors()){
//			return new ModelAndView("home");
//		}
		//BindingResult bindingResult=BindingResult
		log.info("starting product creation");
		//Set<Product> products = new HashSet<Product>();
		Set<Category> catagories = new HashSet<Category>();
		Product product = new Product();
		//product = (Product) context.getBean("product");
		product.setProductName(req.getParameter("productName"));
		product.setShortName(req.getParameter("shortName"));
		product.setReorder(Integer.parseInt(req.getParameter("reorder")));log.error("reorder needs authentication");
		product.setOnHand(Integer.parseInt(req.getParameter("reorder")));
		product.setWeight(Double.parseDouble(req.getParameter("weight")));log.error("weight needs authentication");
		product.setSize(req.getParameter("size"));
		product.setCost(Double.parseDouble(req.getParameter("cost")));log.error("unit cost needs authentication");
		product.setPrice(Double.parseDouble(req.getParameter("price")));log.error("sales cost needs authentication");
		product.setDescription(req.getParameter("description"));
		//products.add(product);
		log.error("before getting parameters");
		String[] catRes =  req.getParameterValues("category");
		log.error(req.getParameter("category"));
		
		log.error(catRes[0]);
		log.error("after getting parameters");
		int i = 0;	
		for (String category : catRes) {
				log.error(category);
				Category obj = new Category();
				if (category!=null){
				obj = bd.getCategory(Integer.parseInt(category));
				log.error(obj.getDescription());
				catagories.add(obj);}
				
				
			}
		log.error("end of loop");
		product.setCatagories(catagories);
		log.info("inserting new product " +product);

		bd.createProduct(product);//creating product with one way mapping
		Product temp = new Product();
		Set<Product> updatedProd = new HashSet<Product>();
		updatedProd.add((Product)bd.selectProduct(product.getProductName()).get(0)) ;
		log.error(updatedProd.isEmpty());
		
		for (String category : catRes) {
			log.error(category);
			Category obj = new Category();
			if (category!=null){
			obj = bd.getCategory(Integer.parseInt(category));
			obj.setProducts(updatedProd);
			bd.updateCategory(obj);
			
			
		}
		}
		return new ModelAndView("redirect:goProducts.do");
		
		
	}
	
	@RequestMapping(value="getAllStates.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllStates() {
		states = bd.getAllStates();
		return states;
	}
	@RequestMapping(value="getAllCategories.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllCategories() {
		catagories =  bd.getAllCategories();
		return catagories;
	}
	
	@RequestMapping(value="getAllInvoices.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllInvoices() {
		invoices = bd.getAllInvoices();
		return invoices;
	}
	
	@RequestMapping(value="getAllPoLines.do", method=RequestMethod.GET,
			produces="application/json")
	@ResponseBody
	public List<Object> getAllPoLines() {
		polines = bd.getAllPoLines();
		return polines;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="insertInvoice.do")
	@ResponseBody
	public ModelAndView insertInvoice(HttpServletRequest request) {
		PurchaseOrder porder = new PurchaseOrder();
		Client cli = (Client) bd.selectClient(request.getParameter("client")).get(0);
		int clitype = Integer.parseInt(request.getParameter("clientType"));
		porder.setClient(cli);
		String[] upcs = request.getParameterValues("upc"), prices = request.getParameterValues("price"),
				onhand = request.getParameterValues("onHand"), quants = request.getParameterValues("quantity"),
				lineids = request.getParameterValues("line");
		Set<POLine> orders = new HashSet<POLine>();
		double subtotal = 0.0;
		for (int i=0; i<upcs.length; i++) {
			POLine pol = new POLine();
			Product p = (Product) bd.selectProduct(upcs[i]).get(0);
			if (clitype == 1)
				p.setOnHand(p.getOnHand() - Integer.parseInt(quants[i]));
			else if (clitype == 2)
				p.setOnHand(p.getOnHand() + Integer.parseInt(quants[i]));
			pol.setProduct(p);
			pol.setPrice(Double.parseDouble(prices[i]));
			pol.setQuantity(Integer.parseInt(quants[i]));
			subtotal += Double.parseDouble(prices[i]) * Integer.parseInt(quants[i]);
			POLineId lineid = new POLineId();
			lineid.setLine(Integer.parseInt(lineids[i]));
			pol.setPoLineId(lineid);
			orders.add(pol);
		}
		porder.setSubtotal(subtotal);
		double tax = 0.7 * subtotal;
		porder.setTax(tax);
		porder.setTotal(subtotal + tax);
		bd.insertPurchaseOrder(porder);
		Iterator<POLine> i = orders.iterator();
		while (i.hasNext()) {
			POLine pol = i.next();
			pol.getPoLineId().setOrder(porder);
			bd.insertPoLine(pol);
		}
		return new ModelAndView("redirect:goInvoices.do");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="insertClient.do")
	@ResponseBody
	public ModelAndView insertClient(HttpServletRequest request) {
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
		return new ModelAndView("redirect:goClients.do");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="updateClient.do")
	@ResponseBody
	public ModelAndView updateClient(HttpServletRequest request) {
		//ClientType type = (ClientType) bd.selectType(request.getParameter("type")).get(0);
		State st = (State) bd.selectState(request.getParameter("state")).get(0);
		Address add = new Address();
		add.setAddressId(Integer.parseInt(request.getParameter("addressId")));
		add.setStreetAddress1(request.getParameter("streetAddress1"));
		add.setStreetAddress2(request.getParameter("streetAddress2"));
		add.setCity(request.getParameter("city"));
		add.setState(st);
		add.setZip(request.getParameter("zip"));
		//bd.updateAddress(add);
		Client client = (Client) bd.selectClient(request.getParameter("clientId")).get(0);
		client.setAddress(add);
		//client.setType(type);
		client.setName(request.getParameter("name"));
		client.setEmail(request.getParameter("email"));
		client.setPhone(request.getParameter("phone"));
		client.setFax(request.getParameter("fax"));
		bd.updateClient(client);
		return new ModelAndView("redirect:goClients.do");
	}
	@RequestMapping(method=RequestMethod.POST, value="updateProduct.do")
	@ResponseBody
	public String updateProduct(HttpServletRequest req){
		Set<Category> catagories = new HashSet<Category>();
		Product product = new Product();
		log.error(req.getParameter("productName"));
		List<Object> pro =	 bd.selectProduct(req.getParameter("productName"));
		product=(Product) pro.get(0);
		//product = (Product) context.getBean("product");
		product.setProductName(req.getParameter("productName"));
		product.setOnHand(Integer.parseInt(req.getParameter("onHand")));
		product.setWeight(Double.parseDouble(req.getParameter("weight")));log.error("weight needs authentication");
		product.setSize(req.getParameter("size"));
		product.setCost(Double.parseDouble(req.getParameter("cost")));log.error("unit cost needs authentication");
		product.setPrice(Double.parseDouble(req.getParameter("price")));log.error("sales cost needs authentication");
		product.setDescription(req.getParameter("description"));
		product.setCatagories(null);
		//products.add(product);
		log.error("before getting parameters");
		String[] catRes =  req.getParameterValues("category");
		log.error(req.getParameter("category"));
		
		log.error(catRes[0]);
		log.error("after getting parameters");
		int i = 0;	
		for (String category : catRes) {
				log.error(category);
				Category obj = new Category();
				if (category!=null){
				obj = bd.getCategory(Integer.parseInt(category));
				log.error(obj.getDescription());
				catagories.add(obj);}
				
				
			}
		log.error("end of loop");
		product.setCatagories(catagories);
		log.info("inserting new product " +product);

		bd.updateProduct(product);//creating product with one way mapping
		Product temp = new Product();
		Set<Product> updatedProd = new HashSet<Product>();
		updatedProd.add((Product)bd.selectProduct(product.getProductName()).get(0)) ;
		log.error(updatedProd.isEmpty());
		
		for (String category : catRes) {
			log.error(category);
			Category obj = new Category();
			if (category!=null){
			obj = bd.getCategory(Integer.parseInt(category));
			obj.setProducts(updatedProd);
			bd.updateCategory(obj);
			
			
		}
		}
		return "products";
	}
	@RequestMapping(method=RequestMethod.POST, value="deleteClient.do")
	@ResponseBody

	public ModelAndView deleteClient(HttpServletRequest request) {
	log.error("Attempting to delete" +request.getParameter("clientId"));
		bd.deleteClient(Integer.parseInt(request.getParameter("clientId")));
		return new ModelAndView("redirect:goClients.do");
	}
	@RequestMapping(method=RequestMethod.POST, value="deleteProduct.do")
	@ResponseBody
	public ModelAndView deleteProduct(HttpServletRequest request) {
	log.error("Attempting to delete" + request.getParameter("upc"));
		bd.deleteProduct(Integer.parseInt(request.getParameter("upc")));
		return new ModelAndView("redirect:goProducts.do");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		
		this.context=context;
		
	}

	
}
