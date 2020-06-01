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

import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.exception.EvChargingException;
import com.capgemini.evCharging.service.EvChargingService;


//@CrossOrigin("http://localhost:4200")
@RestController
public class EvChargingController {

	@Autowired
	EvChargingService chargingService;
	
	@PostMapping("/register/employee/{password}")
	public Boolean registerEmployee(@RequestBody Employee employee, @PathVariable("password") String password) throws EvChargingException   {
		return chargingService.registerEmployee(employee, password);
	}
	
	
	@PostMapping("/add/charger/{stationId}")
	public List<Charger> addNewCharger(@PathVariable("stationID") String  stationId, @RequestBody List<Charger> chargers) throws EvChargingException {
		return chargingService.addChargers(stationId, chargers);
	}
	
//	@DeleteMapping("/remove/charger/{chargerId}")
//	public List<Charger> removeCharger(@PathVariable String chargerId) throws EvChargingException {
//		return chargingService.removeCharger(chargerId, removalDate)
//	}
	
	@GetMapping("/login/{email}/{password}")
	public Boolean loginUser(@PathVariable String email, @PathVariable String password)throws EvChargingException {
		return chargingService.areCredentialsMatched(email, password);
	}
	
	
	
	
	
}
