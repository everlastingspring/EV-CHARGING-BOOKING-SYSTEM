package com.capgemini.evCharging.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Credential;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.Machine;
import com.capgemini.evCharging.bean.MachineDetailKey;
import com.capgemini.evCharging.bean.MachineDetailValue;
import com.capgemini.evCharging.bean.MachineDetails;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.dao.BookingDao;
import com.capgemini.evCharging.dao.CredentialDao;
import com.capgemini.evCharging.dao.EmployeeDao;
import com.capgemini.evCharging.dao.MachineDao;
import com.capgemini.evCharging.dao.StationDao;
import com.capgemini.evCharging.exception.EvChargingException;

public class Utility {
	
	
	public static final Utility utilityObject = new Utility();
	
	public Employee getEmployeeFromMailId(String mailId, EmployeeDao employeeRepo) throws EvChargingException {
		Optional<Employee> optionalEmployeeObj = employeeRepo.findByMailId(mailId);
		if(optionalEmployeeObj.isEmpty()) {
			throw new EvChargingException("Account with" + mailId + " doesn't exist for Employee");
		} 
		return optionalEmployeeObj.get();
	}
	
	public Credential getCredentialFromMailId(String mailId, CredentialDao credentialRepo) throws EvChargingException {
		Optional<Credential> optionalCredentialObj = credentialRepo.findByMailId(mailId);
		if(optionalCredentialObj.isEmpty()) {
			throw new EvChargingException("Account with" + mailId + " doesn't exist for Employee");
		} 
		return optionalCredentialObj.get();
	}
	
	public Machine getMachineFromMachineId(Integer machineId, MachineDao machineRepo) throws EvChargingException {
		Optional<Machine> optionalMachine = machineRepo.findById(machineId);
		if(optionalMachine.isEmpty()) {
			throw new EvChargingException("Machine with MachineId " +  machineId + "doesn't exist");
		}
		return optionalMachine.get();
	}

	public Booking getBookingFromTicketNo(Integer ticketNo, BookingDao bookingRepo) throws EvChargingException {
		Optional<Booking> optionalBooking = bookingRepo.findById(ticketNo);
		if(optionalBooking.isEmpty()) {
			throw new EvChargingException("No such booking with " + ticketNo + " found");
		}

		return optionalBooking.get();
	}

	public Station getStationFromStationId(Integer stationId, StationDao stationRepo) throws EvChargingException {

		Optional<Station> optionalStation = stationRepo.findById(stationId);
		if(optionalStation.isEmpty()) {
			throw new EvChargingException("Station with stationId " +  stationId + "doesn't exist");
		}
		return optionalStation.get();
	}

	
	public MachineDetails populateMachineDetails(MachineDetails machineDetails, List<Machine> machines) {

		for(Machine machine : machines) {
			
			LocalTime time = machine.getStartTime();
	
			for(; time.isBefore(machine.getEndTime());time.plusMinutes(machine.getSlotDuration().getValue())) {
				MachineDetailKey key = new MachineDetailKey(time,time.plusMinutes(machine.getSlotDuration().getValue()));
				
//				if(!detailDictionary.containsKey(key)) {
//					detailDictionary.put(key, new ArrayList<MachineDetailValue>());
//				}
				MachineDetailValue valueElement = new MachineDetailValue(machine.getMachineId());
				machineDetails.getMachineDetails().get(key).add(valueElement);
				
			}
		}
		return machineDetails;

	}

}
