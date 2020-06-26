package com.capgemini.evCharging.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.evCharging.bean.Machine;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.exception.EvChargingException;
import com.capgemini.evCharging.service.EvChargingService;


//@CrossOrigin("http://localhost:4200")
@RestController
public class EvChargingController {

	@Autowired
	EvChargingService chargingService;
	
	@PostMapping("/register/employee/{password}/{isAdmin}")
	public Boolean registerEmployee(@RequestBody Employee employee, @PathVariable("password") String password, @PathVariable("isAdmin") Boolean isAdmin) throws EvChargingException   {
		return chargingService.registerEmployee(employee, password,isAdmin);
	}
	
	
	@PostMapping("/add/Machine/{stationId}")
	public List<Machine> addNewMachine(@PathVariable("stationID") Integer  stationId, @RequestBody List<Machine> machines) throws EvChargingException {
		return chargingService.addMachines(stationId, machines);
	}
	
//	@DeleteMapping("/remove/Machine/{MachineId}")
//	public List<Machine> removeMachine(@PathVariable String MachineId) throws EvChargingException {
//		return chargingService.removeMachine(MachineId, removalDate)
//	}
	
	@GetMapping("/login/{email}/{password}")
	public Boolean loginUser(@PathVariable String email, @PathVariable String password)throws EvChargingException {
		return chargingService.areCredentialsMatched(email, password);
	}
	
	
	
	
	
}
