package com.capgemini.evCharging.controller;



import java.time.LocalTime;
import java.sql.Date;
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
	public Boolean loginUser(@PathVariable String email, @PathVariable String password)throws EvChargingException {
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
	
	@GetMapping("/get/bookingDetail/{selectedDate}/{selectedMachineType}/{stationId}")
	public MachineDetails getMachineBookingDetailByType(@PathVariable Date selectedDate, @PathVariable MachineType selectedMachineType, @PathVariable Integer stationId) throws EvChargingException {
		return chargingService.getMachineBookingDetail(selectedDate, selectedMachineType, stationId);
	}
	
	@GetMapping("/get/allBookings/{empId}")
	public List<Booking> getEmployeeAllBookings(@PathVariable Integer empId) throws EvChargingException {
		return chargingService.getEmployeeAllBookings(empId);
	}
	
	@GetMapping("/get/currentBooking/{empId}")
	public List<Booking> getEmployeeCurrentBookings(@PathVariable Integer empId) throws EvChargingException {
		return chargingService.getEmployeeCurrentBookings(empId);
	}
	
	@PostMapping("/bookMachine/{bookedDate}/{bookingStartTime}/{machineId}/{employeeId}") 
	public List<Booking> bookMachine(@PathVariable Date bookedDate,LocalTime bookingStartTime,Integer machineId, Integer employeeId) throws EvChargingException {
		return chargingService.bookMachine(bookedDate, bookingStartTime, machineId, employeeId);
	}
	
	@PutMapping("/cancelBooking/{ticketNo}") 
	public List<Booking> cancelBooking(@PathVariable Integer ticketNo) throws EvChargingException {
		return chargingService.cancelBooking(ticketNo);
	}
	
	@PutMapping("/rescheduleBooking/{rescheduleTicketNo}/{rescheduledBookedDate}/{rescheduledBookingStartTiming}/{machineId}/{employeeId}")
	public List<Booking> rescheduleBooking(Integer rescheduleTicketNo,Date rescheduledBookedDate, LocalTime rescheduledBookingStartTiming, Integer machineId, Integer employeeId) throws EvChargingException {
		return chargingService.rescheduleBooking(rescheduleTicketNo, rescheduledBookedDate, rescheduledBookingStartTiming, machineId, employeeId);
	}
	
	
	//Admin actions
	@GetMapping("/get/bookingDetail/{selectedDate}/{selectedSlotDuration}/{stationId}")
	public MachineDetails getMachineBookingDetailBySlot(@PathVariable Date selectedDate, @PathVariable SlotDuration selectedSlotDuration, @PathVariable Integer stationId) throws EvChargingException {
		return chargingService.getMachineBookingDetail(selectedDate, selectedSlotDuration, stationId);
	}
	
	@PostMapping("/add/machines/{stationId}")
	public List<Machine> addMachines(@PathVariable("stationID") Integer stationId, @RequestBody List<Machine> machines) throws EvChargingException {
		return chargingService.addMachines(stationId, machines);
	}
	
	@DeleteMapping("/remove/machine/{machineId}")
	public List<Machine> removeMachine(@PathVariable Integer machineId) throws EvChargingException {
		return chargingService.removeMachine(machineId);
	}
	
	@PutMapping("/haltMachine/{machineId}/{newStartDate}")
	public Machine haltMachine(@PathVariable Integer machineId,@PathVariable Date newStartDate) throws EvChargingException {
		return chargingService.haltMachine(machineId, newStartDate);
	}
	
	@PutMapping("/haltMachine/normalMaintenance/{machineId}/{newStartDate}/{newStartTime}/{newEndTime}") 
	public Machine haltMachine(@PathVariable Integer machineId,@PathVariable Date newStartDate,@PathVariable LocalTime newStartTime,@PathVariable LocalTime newEndTime) throws EvChargingException{
		return chargingService.haltMachine(machineId, newStartDate, newStartTime, newEndTime);
	}
	
	@PutMapping("/resumeMachine/{machineId}")
	public Machine resumeMachine(@PathVariable Integer machineId) throws EvChargingException {
		return chargingService.resumeMachine(machineId);
	}
	
	@PutMapping("/modifyMachine")
	public Machine modifyMachine(@RequestBody Machine modifiedMachine) throws EvChargingException {
		return chargingService.modifyMachine(modifiedMachine);
	}
	
	@GetMapping("/generateReport/{fromDate}/{toDate}/{stationId}")
	public List<Booking> generateBookingsReport(@PathVariable Date fromDate,@PathVariable Date toDate,Integer stationId) {
		return chargingService.generateBookingsReport(stationId,fromDate, toDate);
	}
	
	@GetMapping("/generateReport/machine/{fromDate}/{toDate}/{machineId}")
	public List<Booking> generateMachineBookingsReport(@PathVariable Date fromDate,@PathVariable Date toDate,Integer machineId) {
		return chargingService.generateMachineBookingsReport(machineId, fromDate, toDate);
	}
	
	//Non-UI method
	@PostMapping("/add/station/{city}/{campusLocation}")
	public List<Station> addStation(@PathVariable String city,@PathVariable String campusLocation){
		return chargingService.addStation(city, campusLocation);
	}
}
