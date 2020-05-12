package com.capgemini.dao;

import com.capgemini.beans.Employee_data;

public interface EmployeeDAO {
	
	public Boolean login(Employee_data employee_data);
	public Boolean register(Employee_data employee_data);
}
