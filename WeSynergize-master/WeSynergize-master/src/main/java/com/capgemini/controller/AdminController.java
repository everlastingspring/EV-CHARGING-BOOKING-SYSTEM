package com.capgemini.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.beans.Admin;
import com.capgemini.services.AdminServices;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*",allowedHeaders="*")
public class AdminController {
	@Autowired
	AdminServices adminService;
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean loginService(@RequestBody Admin  user) {
		return adminService.loginService(user.getUser_name(), user.getPassword());
	}
	
	

}
