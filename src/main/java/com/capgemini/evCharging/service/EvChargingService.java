package com.capgemini.evCharging.service;

import java.util.Date;
import java.util.List;

import com.capgemini.evCharging.bean.Admin;
import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.ChargerDetail;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
import com.capgemini.evCharging.exception.EvChargingException;

public interface EvChargingService {
	
	//Authenticate  
	public Boolean areCredentialsMatched(String mailId,String password) throws EvChargingException ;
	
	public Boolean registerEmployee(Employee emp, String password) throws EvChargingException;
	
	public Boolean registerAdmin(Admin admin, String password) throws EvChargingException;
	
	
	
	//User Registration drop down
	public List<Station> getChargingStations();
	
	public List<Charger> getChargersOfStation(String stationId) throws EvChargingException;
	
	public List<Charger> getChargersOfStationAndType(String stationId, ChargerType chargerType) throws EvChargingException; // more filtered -> Admin Actions
	
	//User booking 
	public Date getNextAvailableBookingDate(ChargerType selectedChargerType, String selectedStationId);
	
	public List<ChargerDetail> getChargerDetailListForType(Date forDate, ChargerType selectedChargerType, String selectedStationId) throws EvChargingException;
	
	public Booking bookCharger(Date bookedDate, String bookedTiming, String chargerId, String mailId) throws EvChargingException;

	public List<Booking> getBookings(String stationId, String mailId) throws EvChargingException;
	
	public List<Booking> cancelBooking(String ticketNo) throws EvChargingException;
	
	
	//Admin Actions
	
	public List<ChargerDetail> getChargerDetailListForSlot(Date forDate, SlotDuration duration, String stationId) throws EvChargingException;
	
	//Use getChargerDetailListForType
	
	public List<Charger> addChargers(String stationId, List<Charger> chargers) throws EvChargingException;
	
	public List<Charger> removeCharger(String chargerId, Date removalDate) throws EvChargingException;
	
	public Charger haltCharger(String chargerId, Date newStartDate) throws EvChargingException; // for normal maintenance startDate += startDate 
	
	public Charger resumeCharger(String chargerId) throws EvChargingException;
	
	public List<Charger> modifyCharger(String chargerId,SlotDuration newDuration, String[] newChargerActiveTimimgs) throws EvChargingException;
	
	public List<Booking> getBookingsByJoin(Date fromDate, Date toDate, String stationId);
	
	public List<Booking> getBookingsDetail(String chargerId,Date fromDate, Date toDate);
	
	
	
	
	
	//select count(*),charger.chargerId, charger.station.location from Booking where bookingDate >= startingDate and bookingDate <= stoppingDate groupBy charger.chargerId;
}
