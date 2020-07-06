package com.capgemini.evCharging.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.ReportFormat;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.exception.EvChargingException;

public interface EvChargingService {
	
	//Authenticate  
	public Boolean areCredentialsMatched(String mailId,String password) throws EvChargingException ;
	
	public Boolean registerEmployee(Employee emp) throws EvChargingException;
	
	public Boolean registerAdmin(Employee admin) throws EvChargingException;
	
	
	
	//User Registration drop down
	public List<Charger> getChargersOfStation(String campus, String city) throws EvChargingException;
	
	public List<Charger> getChargersOfStationAndType(String campus, String city, ChargerType chargerType) throws EvChargingException; // more filtered -> Admin Actions
	
	//User booking 
	public Date getNextAvailableBookingDate(ChargerType selectedChargerType, String selectedStationId);
	
	public List<Booking> searchSlots(LocalDate forDate, ChargerType selectedChargerType,String campus, String city) throws EvChargingException;
	
	public Booking bookCharger(LocalDate bookedDate, LocalTime startTime, String chargerId, String employeeId) throws EvChargingException;

	public List<Booking> getBookingsAtStationByEmployee(String campus, String city,String mailId) throws EvChargingException;
	
	public Boolean cancelBooking(String ticketNo) throws EvChargingException;
	
	
	//Admin Actions
	
	public List<Booking> getChargerDetailListForSlot(LocalDate forDate, LocalTime duration, String campus,String city) throws EvChargingException;
	
	//Use getChargerDetailListForType here time is sql type or string
	
	public List<Charger> addChargers(List<Charger> chargers) throws EvChargingException;
	
	public Boolean removeCharger(String chargerId) throws EvChargingException;
	
	public Charger haltCharger(String chargerId, LocalDate newStartDate) throws EvChargingException; // for normal maintenance startDate += startDate 
	
	public Charger resumeCharger(String chargerId) throws EvChargingException;
	
	public Charger modifyCharger(Charger charger) throws EvChargingException;
	
	public List<Station> getChargersStations();

	public Charger haltMachine(String machineId, LocalDate newStartDate, LocalTime newStartTime, LocalTime newEndTime) throws EvChargingException;

	public List<Booking> getEmployeeAllBookings(String empId) throws EvChargingException;

	public List<Booking> getEmployeeCurrentBookings(String empId) throws EvChargingException;

	public List<Booking> rescheduleBooking(String rescheduleTicketNo, LocalDate rescheduledBookedDate,
			LocalTime rescheduledBookingStartTiming, String machineId, String employeeId) throws EvChargingException;

	public List<Booking> getBookingsBySlotDuartion(LocalDate selectedDate, LocalTime selectedSlotDuration,
			String stationId) throws EvChargingException;

	public List<ReportFormat> generateBookingsReport(String stationId, LocalDate fromDate, LocalDate toDate) throws EvChargingException;

	public List<Booking> generateMachineBookingsReport(String machineId, LocalDate fromDate, LocalDate toDate)throws EvChargingException;

	public List<Station> addStation(String city, String campusLocation) throws EvChargingException;
		
}