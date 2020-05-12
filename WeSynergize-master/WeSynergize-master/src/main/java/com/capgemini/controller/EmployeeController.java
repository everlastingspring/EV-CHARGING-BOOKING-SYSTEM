package com.capgemini.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.beans.Employee_data;
import com.capgemini.services.EmployeeServices;


@RestController
@RequestMapping("/employee")
@CrossOrigin(origins="*",allowedHeaders="*")
public class EmployeeController {
	@Autowired
	EmployeeServices employeeService;
	
	@PostMapping(path = "/login",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Boolean loginEmployee(@RequestBody Employee_data  user) {
		return employeeService.loginService(user);
	}
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean register(@RequestBody Employee_data employee_data) {
		return employeeService.registerService(employee_data);
	}

}
