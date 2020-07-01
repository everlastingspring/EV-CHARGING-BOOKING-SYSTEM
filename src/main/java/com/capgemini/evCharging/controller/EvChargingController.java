package com.capgemini.evCharging.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters.DateTimeToDateConverter;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.ChargerType;
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
	public Boolean registerEmployee(@RequestBody Employee employee) throws EvChargingException   {
		return chargingService.registerEmployee(employee);
	}
	
	@PostMapping("/register/special-employee")
	public Boolean registerSpecialEmployee(@RequestBody Employee employee) throws EvChargingException   {
		return chargingService.registerAdmin(employee);
	}
	
	
	@PostMapping("/add/chargers")
	public List<Charger> addNewCharger(@RequestBody List<Charger> chargers) throws EvChargingException {
		return chargingService.addChargers(chargers);
	}
	
	@GetMapping("/view/slots/{forDate}/{selectedChargerType}/{campus}-{city}")// yyyy/MM/dd hh:mm:ss a. 
	public List<Booking> viewSlots(@PathVariable("forDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate forDate,@PathVariable("selectedChargerType") ChargerType selectedChargerType, @PathVariable("campus") String campus,@PathVariable("city") String city) throws EvChargingException {
		return chargingService.searchSlots(forDate, selectedChargerType, campus, city);
	}
	
	@GetMapping("/book/charger-for-me/{toThisDate}/{time}/{charger}/{myId}")
	public Booking bookSlot(@PathVariable("toThisDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@PathVariable("time") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time,@PathVariable("charger") String charger,@PathVariable("myId")String employee) throws EvChargingException {
		return chargingService.bookCharger(date, time, charger, employee);
	}
	
	
	@GetMapping("/chargers/locations")
	public List<Station> getChargingStations() throws EvChargingException {
		return chargingService.getChargersStations();
	}
	
	@DeleteMapping("/remove/charger/{chargerId}")
	public List<Charger> removeCharger(@PathVariable("chargerId") String chargerId) throws EvChargingException {
		return chargingService.removeCharger(chargerId);
	}
	
}
// no method right we should add boobk charger method khol ke dekh lo bro mujhe bhi yaad nhi