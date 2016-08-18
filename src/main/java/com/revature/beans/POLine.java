package com.revature.beans;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="IMS_PO_LINE")
public class POLine {
	
@EmbeddedId
private POLineId poLineId;

@Column(name="UNIT_PRICE",nullable=false)
private double price;

@Column(name="QUANTITY_ORDERED",nullable=false)
private int quantity;

@ManyToOne
@JoinColumn(name="PRODUCT_UPC",nullable=false)
private Product product;

public POLineId getPoLineId() {
	return poLineId;
}

public void setPoLineId(POLineId poLineId) {
	this.poLineId = poLineId;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public POLine(POLineId poLineId, double price, int quantity, Product product) {
	super();
	this.poLineId = poLineId;
	this.price = price;
	this.quantity = quantity;
	this.product = product;
}

public POLine() {
	super();
}


}

