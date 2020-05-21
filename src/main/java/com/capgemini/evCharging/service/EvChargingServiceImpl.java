package com.capgemini.evCharging.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.ChargerInfo;
import com.capgemini.evCharging.bean.ChargerType;
import com.capgemini.evCharging.bean.Credential;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.SlotDuration;
import com.capgemini.evCharging.bean.Station;
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
	public Date getNextAvailableChargerDate(ChargerType selectedChargerType, Station selectedChargerStation) {

		return null;
	}

	@Override
	public List<Charger> getChargers(ChargerType selectedChargerType, String stationId) throws EvChargingException {
		try {
			return chargerRepo.getChargers(selectedChargerType, stationId);
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public List<Booking> getBookedChargers(Date selectedDate, ChargerType selectedChargerType,
			Station selectedChargerStation) {

		return null;
	}

	@Override
	public List<ChargerInfo> getChargersInfo(Date selectedDate, ChargerType selectedChargerType,
			Station selectedChargerStation) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Charger> addCharger(Charger charger) throws EvChargingException {
		try {
			if (chargerRepo.findById(charger.getChargerId()).isPresent()) {
				throw new EvChargingException("Charger already exists!");
			}
			chargerRepo.save(charger);
			return chargerRepo.findAll();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public List<Charger> removeCharger(String chargerId) throws EvChargingException {
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

	@Override
	public Charger haltCharger(String chargerId, Date startDate, Date stopDate) throws EvChargingException {
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
			if (optionalCharger.isEmpty()) {
				throw new EvChargingException("Charger doesn't exist");
			}
			Charger charger = optionalCharger.get();
			charger.setstartingDate(startDate);
			charger.setStoppingDate(stopDate);
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
			charger.setIsActive(true);
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
