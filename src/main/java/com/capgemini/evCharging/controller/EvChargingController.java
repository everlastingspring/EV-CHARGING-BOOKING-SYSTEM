package com.capgemini.evCharging.controller;



import java.time.LocalTime;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.evCharging.bean.Machine;
import com.capgemini.evCharging.bean.MachineDetails;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.MachineType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.exception.EvChargingException;
import com.capgemini.evCharging.service.EvChargingService;


//@CrossOrigin("http://localhost:4200")
@RestController
public class EvChargingController {

	@Autowired
	EvChargingService chargingService;
	
	
	//User Actions
	@PostMapping("/register/employee/{password}/{isAdmin}")
	public Boolean registerEmployee(@RequestBody Employee employee, @PathVariable("password") String password, @PathVariable("isAdmin") Boolean isAdmin) throws EvChargingException   {
		return chargingService.registerEmployee(employee, password,isAdmin);
	}
	
	@GetMapping("/login/{email}/{password}")
	public Employee loginUser(@PathVariable String email, @PathVariable String password)throws EvChargingException {
		return chargingService.areCredentialsMatched(email, password);
	}
	
	@GetMapping("/get/stations")
	public List<Station> getAllStations() {
		return chargingService.getStations();
		
	}
	
	//Employee Actions
	@GetMapping("/get/nextDate/{forMachineType}/{stationId}") 
	public Date getNextAvailableBookingDate(@PathVariable MachineType forMachineType, @PathVariable Integer stationId) throws EvChargingException {
		return chargingService.getNextAvailableBookingDate(forMachineType, stationId);
	}
	
	@GetMapping("/get/bookingDetailByType/{selectedDate}/{selectedMachineType}/{stationId}")
	public MachineDetails getMachineBookingDetailByType(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date selectedDate, @PathVariable MachineType selectedMachineType, @PathVariable Integer stationId) throws EvChargingException {
		
		Date selectedSqlDate = new Date(selectedDate.getTime());
		System.out.println(selectedSqlDate);
		
		return chargingService.getMachineBookingDetail(selectedSqlDate, selectedMachineType, stationId);
	}
	
	@GetMapping("/get/allBookings/{empId}")
	public List<Booking> getEmployeeAllBookings(@PathVariable Integer empId) throws EvChargingException {
		return chargingService.getEmployeeAllBookings(empId);
	}
	
	@GetMapping("/get/currentBookings/{empId}")
	public List<Booking> getEmployeeCurrentBookings(@PathVariable Integer empId) throws EvChargingException {
		return chargingService.getEmployeeCurrentBookings(empId);
	}
	
	@PostMapping("/bookMachine/{bookedDate}/{bookingStartTime}/{machineId}/{employeeId}") 
	public List<Booking> bookMachine(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date bookedDate,@PathVariable  @DateTimeFormat(pattern = "HH:mm:ss") LocalTime bookingStartTime,@PathVariable Integer machineId,@PathVariable Integer employeeId) throws EvChargingException {
		Date bookedSqlDate = new Date(bookedDate.getTime());
		return chargingService.bookMachine(bookedSqlDate, bookingStartTime, machineId, employeeId);
	}
	
	@PutMapping("/cancelBooking/{ticketNo}") 
	public List<Booking> cancelBooking(@PathVariable Integer ticketNo) throws EvChargingException {
		return chargingService.cancelBooking(ticketNo);
	}
	
	//To be
	@PutMapping("/rescheduleBooking/{rescheduleTicketNo}/{rescheduledBookedDate}/{rescheduledBookingStartTiming}/{machineId}/{employeeId}")
	public List<Booking> rescheduleBooking(@PathVariable Integer rescheduleTicketNo,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date rescheduledBookedDate,@PathVariable @DateTimeFormat(pattern = "HH:mm:ss") LocalTime rescheduledBookingStartTiming,@PathVariable Integer machineId,@PathVariable Integer employeeId) throws EvChargingException {
		
		return chargingService.rescheduleBooking(rescheduleTicketNo, new Date(rescheduledBookedDate.getTime()), rescheduledBookingStartTiming, machineId, employeeId);
	}
	
	
	//Admin actions
	
	//To be
	@GetMapping("/get/bookingDetailBySlot/{selectedDate}/{selectedSlotDuration}/{stationId}")
	public MachineDetails getMachineBookingDetailBySlot(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date selectedDate, @PathVariable SlotDuration selectedSlotDuration, @PathVariable Integer stationId) throws EvChargingException {
		return chargingService.getMachineBookingDetail(new Date(selectedDate.getTime()), selectedSlotDuration, stationId);
	}
	
	@PostMapping("/add/machines/{stationId}")
	public List<Machine> addMachines(@PathVariable("stationId") Integer stationId, @RequestBody List<Machine> machines) throws EvChargingException {
		return chargingService.addMachines(stationId, machines);
	}
	
	//To be
	@DeleteMapping("/remove/machine/{machineId}")
	public List<Machine> removeMachine(@PathVariable Integer machineId) throws EvChargingException {
		return chargingService.removeMachine(machineId);
	}
	
	//To be
	@PutMapping("/haltMachine/{machineId}/{newStartDate}")
	public Machine haltMachine(@PathVariable Integer machineId,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date newStartDate) throws EvChargingException {
		return chargingService.haltMachine(machineId, new Date(newStartDate.getTime()));
	}
	
	//To be
	@PutMapping("/haltMachine/normalMaintenance/{machineId}/{newStartDate}/{newStartTime}/{newEndTime}") 
	public Machine haltMachine(@PathVariable Integer machineId,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date newStartDate,@PathVariable @DateTimeFormat(pattern = "HH:mm:ss") LocalTime newStartTime,@PathVariable @DateTimeFormat(pattern = "HH:mm:ss") LocalTime newEndTime) throws EvChargingException{
		return chargingService.haltMachine(machineId, new Date(newStartDate.getTime()), newStartTime, newEndTime);
	}
	
	//To be
	@PutMapping("/resumeMachine/{machineId}")
	public Machine resumeMachine(@PathVariable Integer machineId) throws EvChargingException {
		return chargingService.resumeMachine(machineId);
	}
	
	//To be
	@PutMapping("/modifyMachine")
	public Machine modifyMachine(@RequestBody Machine modifiedMachine) throws EvChargingException {
		return chargingService.modifyMachine(modifiedMachine);
	}
	
	//To be
	@GetMapping("/generateReport/{fromDate}/{toDate}/{stationId}")
	public List<Booking> generateBookingsReport(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date toDate,@PathVariable Integer stationId) {
		return chargingService.generateBookingsReport(stationId,new Date(fromDate.getTime()), new Date(toDate.getTime()));
	}
	
	//To be
	@GetMapping("/generateReport/machine/{fromDate}/{toDate}/{machineId}")
	public List<Booking> generateMachineBookingsReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date toDate,@PathVariable Integer machineId) {
		return chargingService.generateMachineBookingsReport(machineId, new Date(fromDate.getTime()), new Date(toDate.getTime()));
	}
	
	//Non-UI method
	@PostMapping("/add/station/{city}/{campusLocation}")
	public List<Station> addStation(@PathVariable String city,@PathVariable String campusLocation){
		return chargingService.addStation(city, campusLocation);
	}
}
