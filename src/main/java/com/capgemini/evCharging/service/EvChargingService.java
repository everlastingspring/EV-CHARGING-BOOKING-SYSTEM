package com.capgemini.evCharging.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
import com.capgemini.evCharging.exception.EvChargingException;

public interface EvChargingService {
	
	//Authenticate  
	public Boolean areCredentialsMatched(String mailId,String password) throws EvChargingException ;
	
	public Boolean registerEmployee(Employee emp) throws EvChargingException;
	
	public Boolean registerAdmin(Employee admin) throws EvChargingException;
	
	
	
	//User Registration drop down
	public List<Charger> getChargersOfStation(String campus, String city) throws EvChargingException;
	
	public List<Charger> getChargersFromCampusAndCity(String stationId) throws EvChargingException;
	
	public List<Charger> getChargersOfStationAndType(String stationId, ChargerType chargerType) throws EvChargingException; // more filtered -> Admin Actions
	
	//User booking 
	public Date getNextAvailableBookingDate(ChargerType selectedChargerType, String selectedStationId);
	
	public List<Booking> getChargerDetailListForType(Date forDate, ChargerType selectedChargerType,String campus, String city) throws EvChargingException;
	
	public Booking bookCharger(Date bookedDate, String bookedTiming, String chargerId, String mailId) throws EvChargingException;

	public List<Booking> getBookings(String stationId, String mailId) throws EvChargingException;
	
	public List<Booking> cancelBooking(String ticketNo) throws EvChargingException;
	
	
	//Admin Actions
	
	public List<Booking> getChargerDetailListForSlot(Date forDate, SlotDuration duration, String campus,String city) throws EvChargingException;
	
	//Use getChargerDetailListForType here time is sql type or string
	public List<Booking> addBookingData(String chargerId,Date date,String timing);//thats it know  abhi ye code git par upload kar do bro, main aaj raat dekh rha hu
	
	public List<Charger> addChargers(String campus, String city, List<Charger> chargers) throws EvChargingException;
	
	public List<Charger> removeCharger(String chargerId) throws EvChargingException;
	
	public Charger haltCharger(String chargerId, Date newStartDate) throws EvChargingException; // for normal maintenance startDate += startDate 
	
	public Charger resumeCharger(String chargerId) throws EvChargingException;
	
	public List<Charger> modifyCharger(String chargerId,SlotDuration newDuration, String[] newChargerActiveTimimgs) throws EvChargingException;
	
	public List<Booking> getBookingsByJoin(Date fromDate, Date toDate, String stationId);
	
	public List<Booking> getBookingsDetail(String chargerId,Date fromDate, Date toDate);

	public List<String> getChargersStations();
		
}
