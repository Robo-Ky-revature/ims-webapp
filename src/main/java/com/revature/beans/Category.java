package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="IMS_PRODUCT_CATAGORY")
public class Category {
@Id
@Column(name="CATEGORY_ID",nullable=false)
@GeneratedValue(strategy=GenerationType.AUTO)
private int catagoryId;
@Column(name="CATEGORY_DESCRIPTION",nullable=false)
private String description;
@ManyToMany(fetch=FetchType.EAGER)
@JoinTable(name="PRODUCT_CATEGORIES",joinColumns=@JoinColumn(name="PRODUCT_UPC"),
inverseJoinColumns=@JoinColumn(name="CATEGORY_ID"))
private Set<Product> products;
public int getCatagoryId() {
	return catagoryId;
}
public void setCatagoryId(int catagoryId) {
	this.catagoryId = catagoryId;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Set<Product> getProducts() {
	return products;
}
public void setProducts(Set<Product> products) {
	this.products = products;
}
public Category(int catagoryId, String description, Set<Product> products) {
	super();
	this.catagoryId = catagoryId;
	this.description = description;
	this.products = products;
}
public Category() {
	super();
}


}
