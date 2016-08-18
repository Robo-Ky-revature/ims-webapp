package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="IMS_CLIENT")
public class Client {
@Id
@Column(name="IMS_CLIENT_ID",nullable=false)
@GeneratedValue(strategy=GenerationType.AUTO)
private int clientId;
@Column(name="CLIENT_NAME",nullable=false)
private String name;
@Column(name="CLIENT_EMAIL",nullable=false)
private String email;
@Column(name="POINT_OF_CONTACT_NAME",nullable=false)
private String contactName;
@Column(name="CLIENT_PHONE",nullable=false)
private String phone;
@Column(name="CLIENT_FAX",nullable=false)
private String fax;
@OneToOne
@JoinColumn(name="ADDRESS_ID",unique=true,nullable=false)
private Address address;
@ManyToOne
@JoinColumn(name="CLIENT_TYPE_ID",nullable=false)
private ClientType type;
@OneToMany(mappedBy="client")
private Set<PurchaseOrder> orders;

public int getClientId() {
	return clientId;
}
public void setClientId(int clientId) {
	this.clientId = clientId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getContactName() {
	return contactName;
}
public void setContactName(String contactName) {
	this.contactName = contactName;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public ClientType getType() {
	return type;
}
public void setType(ClientType type) {
	this.type = type;
}
public Client(int clientId, String name, String email, String contactName, String phone, String fax, Address address,
		ClientType type) {
	super();
	this.clientId = clientId;
	this.name = name;
	this.email = email;
	this.contactName = contactName;
	this.phone = phone;
	this.fax = fax;
	this.address = address;
	this.type = type;
}
public Client() {
	super();
}


}
