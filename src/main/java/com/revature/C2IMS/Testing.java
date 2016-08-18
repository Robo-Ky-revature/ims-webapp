package com.revature.C2IMS;

import java.util.List;

import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.State;
import com.revature.database.DataLayer;

public class Testing {
public static void main(String[] args) {
//	String[] stateArr = new String[] {"Alabama", "Arkansas", "Arizona", "Alaska", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };
//	String[] abbreviations = new String[]{"AL","AR","AZ","AK","CA","CO","CT","DE","FL","GA","HI","IA","ID", "IL","IN","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND", "OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
//	//State obj = new State(0, "Alabama", "AL");
//	State state= new State();
//	DataLayer dl = new DataLayer();
//	for(int i=1;i<50;i++){
//		System.out.print(stateArr[i] + " ");
//		System.out.println(abbreviations[i]);
//		state = new State(i,stateArr[i],abbreviations[i]);
//		dl.createRow(state);
//	}
//	ClientType ct= new ClientType(1, "Supplier");
//	DataLayer dl = new DataLayer();
//	dl.createRow(ct);
	DataLayer dl = new DataLayer();
	List<Object> clients = dl.selectAllRows(new Client());
	if (clients != null)
		System.out.println(((Client) clients.get(0)).getName());
	else
		System.out.println("no list");
}
}
