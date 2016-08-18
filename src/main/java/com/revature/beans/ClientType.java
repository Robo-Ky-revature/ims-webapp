package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMS_CLIENT_TYPE")
public class ClientType {
@Id
@Column(name="CLIENT_TYPE_ID",nullable=false)
@GeneratedValue(strategy=GenerationType.AUTO)
private int clientId;
@Column(name="CLIENT_TYPE",nullable=false)
private String type;
public int getClientId() {
	return clientId;
}
public void setClientId(int clientId) {
	this.clientId = clientId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public ClientType(int clientId, String type) {
	super();
	this.clientId = clientId;
	this.type = type;
}
public ClientType() {
	super();
}


}
