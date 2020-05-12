package com.capgemini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.AdminDao;

@Service
public class AdminServicesImpl implements AdminServices {
	@Autowired
	private AdminDao adminDao;

	@Override
	public Boolean loginService(String username, String password) {
		return adminDao.login(username, password);
	}

}
