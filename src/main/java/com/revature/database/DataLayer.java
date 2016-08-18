package com.revature.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.State;

@Service
public class DataLayer {
	
	@Autowired
	DAO dao;
	private Session session;
	public DataLayer(){
		session=SessionFactoryManager.getInstance().openSession();
		dao = new DAO(session);
	}
	
	public void close(){
		if(session!=null)session.close();
	}
	
	public void createRow(Object obj){
		Transaction trans = session.beginTransaction();
		try{
		dao.insert(obj);
		trans.commit();
		}catch(RuntimeException c){
			trans.rollback();
		}
	}
	
	public List<Object> selectAllRows(Object obj){
		return dao.selectAll(obj);
	}
}
