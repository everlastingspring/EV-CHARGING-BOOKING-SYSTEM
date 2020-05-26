package com.capgemini.evCharging.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Credential;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
import com.capgemini.evCharging.dao.AdminDao;
import com.capgemini.evCharging.dao.BookingDao;
import com.capgemini.evCharging.dao.ChargerDao;
import com.capgemini.evCharging.dao.CredentialDao;
import com.capgemini.evCharging.dao.EmployeeDao;
import com.capgemini.evCharging.dao.StationDao;
import com.capgemini.evCharging.exception.EvChargingException;

@Service
public class EvChargingServiceImpl implements EvChargingService {

	@Autowired
	AdminDao adminRepo;

	@Autowired
	BookingDao bookingRepo;

	@Autowired
	ChargerDao chargerRepo;

	@Autowired
	CredentialDao credentialRepo;

	@Autowired
	EmployeeDao employeeRepo;

	@Autowired
	StationDao stationRepo;

	@Override
	public Boolean areCredentialsMatched(String mailId, String password) throws EvChargingException {

		try {
			Optional<Credential> optionalCredential = credentialRepo.findById(mailId);
			if (optionalCredential.isPresent()) {
				Credential credential = optionalCredential.get();
				String hashUserPassword = HashAlgorithmService.hashedPassword(password, credential.getSaltArray());
				if (hashUserPassword.equals(credential.getHashedPassword())) {
					return true;
				}
				return false;

			} else {
				throw new EvChargingException("Mail Id is not registered!");
			}

		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public Boolean registerEmployee(Employee emp, String password) throws EvChargingException {
		try {
			Credential credential = new Credential();
			credential.setMailId(emp.getMailId());
			byte[] salt = HashAlgorithmService.createSalt();
			String hashedPassword = HashAlgorithmService.hashedPassword(password, salt);
			credential.setHashedPassword(hashedPassword);
			credential.setSaltArray(salt);
			credentialRepo.save(credential);

			employeeRepo.save(emp);

			return true;
		} catch (Exception exception) {

			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public List<Station> getChargingStations() {
		return stationRepo.findAll();
	}

	
	@Override
	public List<Booking> cancelBooking(String bookingId) throws EvChargingException {
		try {
			Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);
			if (optionalBooking.isEmpty()) {
				throw new EvChargingException("Booking doesn't exist");

			}
			bookingRepo.delete(optionalBooking.get());
			return bookingRepo.findAll();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public List<Booking> getBookings(String stationId, String mailId) throws EvChargingException {
		try {
			return bookingRepo.getBookings(stationId, mailId);
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public List<Charger> addChargers(String stationId, List<Charger> chargers) throws EvChargingException {
		Optional<Station> optionalStation = stationRepo.findById(stationId);
		if(optionalStation.isEmpty()) {
			throw new EvChargingException("Station doesn't exist");
		} 
		Station chargingStation = optionalStation.get();
		
		for (Charger currentCharger : chargers) {
			try {
				if (chargerRepo.findById(currentCharger.getChargerId()).isPresent()) {
					throw new EvChargingException("Charger already exists!");
				}
				
				currentCharger.setChargerStation(chargingStation);
				chargerRepo.save(currentCharger);
				
			} catch (Exception exception) {
				throw new EvChargingException(exception.getMessage());
			}
		}
		
		return chargerRepo.findAll();
		

	}

	@Override
	public List<Charger> (String chargerId, Date removalDate) throws EvChargingException {
		try {
			if (chargerRepo.findById(chargerId).isEmpty()) {
				throw new EvChargingException("Charger doesn't exist");
			}
			
			
			chargerRepo.deleteById(chargerId);
			return chargerRepo.findAll();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	
	public Date handleRemovalDate(String chargerId, Date removalDate) throws EvChargingException {
		Date currentDate = new Date();
		int result = currentDate.compareTo(removalDate);
		if(result == 0) {
			removeCharger(chargerId, removalDate);
		}
	}
	
	@Override
	public Charger haltCharger(String chargerId, Date startDate, Date stopDate) throws EvChargingException {
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
			if (optionalCharger.isEmpty()) {
				throw new EvChargingException("Charger doesn't exist");
			}
			Charger charger = optionalCharger.get();
			charger.setStartingDate(startDate);
			charger.setRemovalDate(stopDate);
			chargerRepo.save(charger);
			return chargerRepo.findById(chargerId).get();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public Charger resumeCharger(String chargerId) throws EvChargingException {
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
			if (optionalCharger.isEmpty()) {
				throw new EvChargingException("Charger doesn't exists");
			}
			Charger charger = optionalCharger.get();
			charger.setChargerStatus(ChargerStatus.ACTIVE);
			chargerRepo.save(charger);
			return chargerRepo.findById(chargerId).get();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public List<Charger> modifyCharger(String chargerId, SlotDuration newDuration, String[] newChargerActiveTimimgs) throws EvChargingException{
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
			if (optionalCharger.isEmpty()) {
				throw new EvChargingException("Charger doesn't exists");
			}
			Charger charger = optionalCharger.get();
			charger.setSlotDuration(newDuration);
			charger.setChargerActiveTimings(newChargerActiveTimimgs);
			chargerRepo.save(charger);
			return chargerRepo.findAll();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public List<Booking> getBookings(Charger charger, Station chargerStation, Date fromDate, Date toDate) {
		return null;
	}

}
