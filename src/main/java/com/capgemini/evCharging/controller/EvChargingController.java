package com.capgemini.evCharging.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.ReportFormat;
//import com.capgemini.evCharging.bean.ReportFormat;
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
	public Boolean removeCharger(@PathVariable("chargerId") String chargerId) throws EvChargingException {
		return chargingService.removeCharger(chargerId);
	}
	
	@PutMapping("/cancelBooking/{bookingId}") 
	public Boolean cancelBooking(@PathVariable("bookingId") String bookingId) throws EvChargingException {
		return chargingService.cancelBooking(bookingId);
	}
	
	@PutMapping("/haltcharger/{chargerId}/{newStartDate}")
	public Charger haltMachine(@PathVariable("chargerId") String chargerId,@PathVariable("newStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newStartDate) throws EvChargingException {
		return chargingService.haltCharger(chargerId, newStartDate);
	}
	
	
	@PutMapping("/haltMachine/normalMaintenance/{chargerId}/{newStartDate}/{newStartTime}/{newEndTime}") 
	public Charger haltMachine(@PathVariable("chargerId") String chargerId,@PathVariable("newStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newStartDate,@PathVariable("newStartTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime newStartTime,@PathVariable("newEndTime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime newEndTime) throws EvChargingException{
		return chargingService.haltMachine(chargerId,newStartDate, newStartTime, newEndTime);
	}
	
	
	@PutMapping("/resumecharger/{chargerId}")
	public Charger resumeMachine(@PathVariable("chargerId") String chargerId) throws EvChargingException {
		return chargingService.resumeCharger(chargerId);
	}
	
	
	@PutMapping("/modify-charger")
	public Charger modifyMachine(@RequestBody Charger charger) throws EvChargingException {
		return chargingService.modifyCharger(charger);
	}
	
	@GetMapping("/get/allBookings/{empId}")
	public List<Booking> getEmployeeAllBookings(@PathVariable String empId) throws EvChargingException {
		return chargingService.getEmployeeAllBookings(empId);
	}
	
	@GetMapping("/get/currentBookings/{empId}")
	public List<Booking> getEmployeeCurrentBookings(@PathVariable String empId) throws EvChargingException {
		return chargingService.getEmployeeCurrentBookings(empId);
	}
	
	@PutMapping("/rescheduleBooking/{rescheduleTicketNo}/{rescheduledBookedDate}/{rescheduledBookingStartTiming}/{machineId}/{employeeId}")
	public List<Booking> rescheduleBooking(@PathVariable String rescheduleTicketNo,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate rescheduledBookedDate,@PathVariable @DateTimeFormat(pattern = "HH:mm:ss") LocalTime rescheduledBookingStartTiming,@PathVariable String machineId,@PathVariable String employeeId) throws EvChargingException {
		
		return chargingService.rescheduleBooking(rescheduleTicketNo, rescheduledBookedDate, rescheduledBookingStartTiming, machineId, employeeId);
	}
	
	@GetMapping("/get/bookingDetailBySlot/{selectedDate}/{selectedSlotDuration}/{stationId}")
	public List<Booking> getChargerBookingDetailBySlot(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate, @PathVariable @DateTimeFormat(pattern = "HH:mm:ss") LocalTime selectedSlotDuration, @PathVariable String stationId) throws EvChargingException {
		return chargingService.getBookingsBySlotDuartion(selectedDate, selectedSlotDuration, stationId);
	}
	
	@GetMapping("/generateReport/{fromDate}/{toDate}/{stationId}")
	public List<ReportFormat> generateBookingsReport(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,@PathVariable String stationId) throws EvChargingException {
		return chargingService.generateBookingsReport(stationId,fromDate, toDate);
	}
	
	
	@GetMapping("/generateReport/machine/{fromDate}/{toDate}/{machineId}")
	public List<Booking> generateMachineBookingsReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,@PathVariable String machineId)  throws EvChargingException{
		return chargingService.generateMachineBookingsReport(machineId, fromDate, toDate);
	}
	
	//Non-UI method
	@PostMapping("/add/station/{city}/{campusLocation}")
	public List<Station> addStation(@PathVariable String city,@PathVariable String campusLocation) throws EvChargingException{
		return chargingService.addStation(city, campusLocation);
	}
}
// no method right we should add boobk charger method khol ke dekh lo bro mujhe bhi yaad nhi