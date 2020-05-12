package com.capgemini.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.beans.Employee_data;
import com.capgemini.dao.EmployeeDAO;
@Service
public class EmployeeServicesImpl implements EmployeeServices {
	@Autowired
	private EmployeeDAO employeeDAO;
	@Override
	public Boolean loginService(Employee_data employee_data) {
		return employeeDAO.login(employee_data);
	}
	@Override
	public Boolean registerService(Employee_data employee_data) {
		return employeeDAO.register(employee_data);
	}
	
	
}
