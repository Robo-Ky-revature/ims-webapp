package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="IMS_ADDRESS")
public class Address {
	@Id
	@Column(name="CLIENT_NAME",nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;
	@Column(name="STREET_ADDRESS_1",nullable=false)
	private String streetAddress1;
	@Column(name="STREET_ADDRESS_2",nullable=false)
	private String streetAddress2;
	@Column(name="ADDRESS_CITY",nullable=false)
	private String city;
	@ManyToOne
	@JoinColumn(name="STATE_ID",nullable=false)
	private State state;
	@Column(name="ADDRESS_ZIP",nullable=false)
	private String zip;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreetAddress1() {
		return streetAddress1;
	}
	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}
	public String getStreetAddress2() {
		return streetAddress2;
	}
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Address(int addressId, String streetAddress1, String streetAddress2, String city, State state, String zip) {
		super();
		this.addressId = addressId;
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public Address() {
		super();
	}
	
	

}
