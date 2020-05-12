package com.capgemini.services;

import com.capgemini.beans.Employee_data;

public interface EmployeeServices {
	
	public Boolean loginService(Employee_data employee_data);
	public Boolean registerService(Employee_data employee_data);
}
