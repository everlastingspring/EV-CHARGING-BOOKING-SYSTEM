package com.capgemini.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;

import com.capgemini.beans.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory = Persistence.
	           createEntityManagerFactory("electriccar_charging_management");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	
	@Override
	public Boolean login(String username, String password) {
		try {
			Admin admin_data=entityManager.find(Admin.class, username);
			if(admin_data.getUser_name().equals(username)) {
				if(admin_data.getPassword().equals(password)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	entityManager.close();
	return false;
	}
	

	
}
