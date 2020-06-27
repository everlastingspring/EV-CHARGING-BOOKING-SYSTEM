package com.capgemini.evCharging.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.exception.EvChargingException;
import com.capgemini.evCharging.service.EvChargingService;


@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
@RestController
public class EvChargingController {

	@Autowired
	EvChargingService chargingService;
	
	@PostMapping("/user/auth")
	public Boolean areCredentialsMatched(@RequestBody Employee employee) throws EvChargingException {
			return chargingService.areCredentialsMatched(employee.getMailId(), employee.getPassword());
	}
	
	
	
	@PostMapping("/register/employee")
	public Boolean registerEmployee(@RequestBody Employee employee) throws Exception   {
		return chargingService.registerEmployee(employee);
	}
	
	
	@PostMapping("/add/chargers")
	public List<Charger> addNewCharger(@RequestBody List<Charger> chargers) throws EvChargingException {
		return chargingService.addChargers(chargers);
	}
	
	@GetMapping("/login/emp")
	public Boolean loginUser(@RequestBody Employee employee)throws EvChargingException {
		return chargingService.areCredentialsMatched(employee.getMailId(),employee.getPassword());
	}
	
	@GetMapping("/chargers/locations")
	public List<String> getChargingStations() throws EvChargingException {
		return chargingService.getChargersStations();
	}
	
	@DeleteMapping("/remove/charger/{chargerId}")
	public List<Charger> removeCharger(@PathVariable String chargerId) throws EvChargingException {
		return chargingService.removeCharger(chargerId);
	}
	
}
// no method right we should add boobk charger method khol ke dekh lo bro mujhe bhi yaad nhi