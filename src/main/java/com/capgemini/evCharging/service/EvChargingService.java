package com.capgemini.evCharging.service;

import java.sql.Date;
import java.util.List;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.ChargerInfo;
import com.capgemini.evCharging.bean.ChargerType;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.SlotDuration;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.exception.EvChargingException;

public interface EvChargingService {
	
	//Authenticate  
	public Boolean areCredentialsMatched(String mailId,String password) throws EvChargingException ;
	
	public Boolean registerEmployee(Employee emp, String password) throws EvChargingException;
	
	
	public List<Station> getChargingStations();
	
	public Date getNextAvailableChargerDate(ChargerType selectedChargerType, Station selectedChargerStation);
	
	public List<Charger> getChargers(ChargerType selectedChargerType, String stationId) throws EvChargingException ; //isActive  = true

	public List<Booking> getBookedChargers(Date selectedDate, ChargerType selectedChargerType, Station selectedChargerStation);
	
	public List<ChargerInfo> getChargersInfo(Date selectedDate, ChargerType selectedChargerType, Station selectedChargerStation );
	
	//get All chargers, for each Charger check in the database with condns. if they are booked or not and prepare the list of ChargerInfo
	
	public List<Booking> cancelBooking(String bookingId) throws EvChargingException ;  
	
	public List<Booking> getBookings(String stationId, String mailId) throws EvChargingException;
	
	public List<Charger> addCharger(Charger charger) throws EvChargingException;
	
	public List<Charger> removeCharger(String chargerId) throws EvChargingException;
	
	public Charger haltCharger(String chargerId, Date startDate, Date stopDate) throws EvChargingException;
	
	public Charger resumeCharger(String chargerId) throws EvChargingException;
	
	public List<Charger> modifyCharger(String chargerId,SlotDuration newDuration, String[] newChargerActiveTimimgs) throws EvChargingException;
	
	public List<Booking> getBookings(Charger charger,Station chargerStation,Date fromDate, Date toDate);
	
	
	
	
	
	//select count(*),charger.chargerId, charger.station.location from Booking where bookingDate >= startingDate and bookingDate <= stoppingDate groupBy charger.chargerId;
}
