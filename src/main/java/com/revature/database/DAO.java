package com.revature.database;


import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {
	 private Session session;
	public DAO(Session session){
		this.session=session;
	}
	public void insert(Object obj){
		
		session.saveOrUpdate(obj);
	}
	public List<Object> selectAll(Object obj) {
		return session.createCriteria(obj.getClass()).list();
	}
}
