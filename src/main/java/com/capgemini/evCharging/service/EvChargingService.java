package com.capgemini.evCharging.service;

import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.Machine;
import com.capgemini.evCharging.bean.MachineDetails;
import com.capgemini.evCharging.bean.ReportFormat;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.MachineType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
import com.capgemini.evCharging.exception.EvChargingException;

public interface EvChargingService {
	
	//Registration and login
	public Employee areCredentialsMatched(String mailId,String password) throws EvChargingException ; 
	
	public Boolean registerEmployee(Employee emp, String password,Boolean isAdmin) throws EvChargingException;

	public List<Station> getStations();
	
	
	
	//Employee actions
	
	public Date getNextAvailableBookingDate(MachineType selectedMachineType, Integer selectedStationId) throws EvChargingException;
	
	public MachineDetails getMachineBookingDetail(Date selectedDate, MachineType selectedMachineType, Integer stationId) throws EvChargingException;
	
	public List<Booking> bookMachine(Date bookedDate, LocalTime bookedTiming, Integer machineId, Integer employeeId) throws EvChargingException;

	public List<Booking> getEmployeeAllBookings(Integer employeeId) throws EvChargingException;
	
	public List<Booking> getEmployeeCurrentBookings(Integer employeeId) throws EvChargingException;
	
	public List<Booking> cancelBooking(Integer ticketNo) throws EvChargingException;
	
	public List<Booking> rescheduleBooking(Integer ticketNo,Date bookedDate, LocalTime bookingStartTiming, Integer machineId, Integer employeeId) throws EvChargingException;
	
	
	// Admin actions
	
	public MachineDetails getMachineBookingDetail(Date selectedDate, SlotDuration selectedDuration, Integer stationId) throws EvChargingException;
	
	public List<Machine> addMachines(Integer stationId, List<Machine> machines) throws EvChargingException;
	
	public List<Machine> removeMachine(Integer machineId) throws EvChargingException;
	
	public Machine haltMachine(Integer machineId, Date newStartDate, LocalTime newStartTime, LocalTime newEndTime) throws EvChargingException; // for normal maintenance startDate += startDate 
	
	public Machine haltMachine(Integer machineId, Date newStartDate) throws EvChargingException; // for normal maintenance startDate += startDate 
	
	public Machine resumeMachine(Integer machineId) throws EvChargingException;
	
	public Machine modifyMachine(Machine modifiedMachine) throws EvChargingException;
	
	public List<ReportFormat> generateBookingsReport(Integer stationId,Date fromDate, Date toDate);
	
	public List<Booking> generateMachineBookingsReport(Integer machineId,Date fromDate, Date toDate);
	
	
	//Admin actions -> Not in presentation
	public List<Station> addStation(String city,String campusLocation);
	
	
	//select count(*),Machine.MachineId, Machine.station.location from Booking where bookingDate >= startingDate and bookingDate <= stoppingDate groupBy Machine.MachineId;
}
