package com.capgemini.evCharging.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Credential;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.enums.BookingStatus;
import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.dao.BookingDao;
import com.capgemini.evCharging.dao.ChargerDao;
import com.capgemini.evCharging.dao.CredentialDao;
import com.capgemini.evCharging.dao.EmployeeDao;
import com.capgemini.evCharging.exception.EvChargingException;

@Service
public class EvChargingServiceImpl implements EvChargingService {

	@Autowired
	BookingDao bookingRepo;

	@Autowired
	ChargerDao chargerRepo;

	@Autowired
	CredentialDao credentialRepo;

	@Autowired
	EmployeeDao employeeRepo;

	@Override
	public Boolean areCredentialsMatched(String mailId, String password) throws EvChargingException {

		try {
			Optional<Credential> optionalCredential = credentialRepo.findById(mailId);
			if (optionalCredential.isPresent()) {
				Credential credential = optionalCredential.get();
				String hashUserPassword = HashAlgorithmService.hashedPassword(password, credential.getSalt());
				if (hashUserPassword.equals(credential.getPassword())) {
					return true;
				} else {
					throw new EvChargingException("Password is incorrect!");
				}

			} else {
				throw new EvChargingException("Mail Id is not registered!");
			}

		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public Boolean registerEmployee(Employee emp) throws EvChargingException {

		try {
			Credential credential = new Credential();
			byte[] salt = HashAlgorithmService.createSalt();
			String hashedPassword = HashAlgorithmService.hashedPassword(emp.getPassword(), salt);
			credential.setPassword(hashedPassword);
			credential.setSalt(salt);
			employeeRepo.save(emp);
			credentialRepo.save(credential);

			return true;
		} catch (Exception exception) {

			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public Boolean registerAdmin(Employee admin) throws EvChargingException {
		try {
			Credential credential = new Credential();
			credential.setMailId(admin.getMailId());
			byte[] salt = HashAlgorithmService.createSalt();
			String hashedPassword = HashAlgorithmService.hashedPassword(admin.getPassword(), salt);
			credential.setPassword(hashedPassword);
			credential.setSalt(salt);
			credential.setIsAdmin(true);
			credentialRepo.save(credential);
			employeeRepo.save(admin);

			return true;

		} catch (Exception exception) {

			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public List<Charger> getChargersOfStation(String campus, String city) throws EvChargingException {
		List<Charger> chargers = chargerRepo.getChargersOfStation(campus,city);
		if (chargers.isEmpty()) {
			throw new EvChargingException("Charging Station doesn't exist");
		}
		return chargers;
	}

	@Override
	public List<Charger> getChargersOfStationAndType(String campus,String city, ChargerType chargerType)
			throws EvChargingException {
		List<Charger> chargers = chargerRepo.getChargersOfStationAndType(chargerType, campus, city);
		if (chargers.isEmpty()) {
			throw new EvChargingException("Charging Station doesn't exist");
		}
		return chargers;
	}

	@Override
	public Date getNextAvailableBookingDate(ChargerType selectedChargerType, String selectedStationId) {
		return new Date();
	}

	@Override
	public List<Booking> searchSlots(LocalDate forDate, ChargerType selectedChargerType,
			String campus, String city) throws EvChargingException {
		List<Booking> chargerDetails = bookingRepo.searchSlots(forDate, selectedChargerType,campus,city);

		if (chargerDetails.isEmpty()) {
			throw new EvChargingException("Charging Station or Charger Type or selected date doesn't have any data");
		}
		return chargerDetails;
	}

	// Utility functions
	public Charger getChargerFromChargerId(String chargerId) throws EvChargingException {
		Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
		if (!optionalCharger.isPresent()) {
			throw new EvChargingException("Charger with chargerId " + chargerId + "doesn't exist");
		}
		return optionalCharger.get();
	}

	public Employee getEmployeeDetailFromMailId(String mailId) throws EvChargingException {
		Optional<Employee> optionalEmployeeObj = employeeRepo.findById(mailId);
		if (!optionalEmployeeObj.isPresent()) {
			throw new EvChargingException("Account with" + mailId + " doesn't exist for Employee");
		}
		return optionalEmployeeObj.get();
	}

	public Booking getBookingFromTicketNo(String ticketNo) throws EvChargingException {
		Optional<Booking> optionalBooking = bookingRepo.findById(ticketNo);
		if (!optionalBooking.isPresent()) {
			throw new EvChargingException("No such booking with " + ticketNo + " found");
		}

		return optionalBooking.get();
	}
 
	@Override
	public Booking bookCharger(LocalDate bookedDate, String bookedTiming, String chargerId, String mailId) throws EvChargingException {
		
		
		Booking booking = getBookingData(bookedDate,bookedTiming);
		booking.setBookingByEmployee(getEmployeeDetailFromMailId(mailId));
		bookingRepo.saveAndFlush(booking);
		return booking; 
		
	}

	private Booking getBookingData(LocalDate bookedDate, String chargerId) {
		Booking booking=bookingRepo.getBookingForDateTime(bookedDate,chargerId);
		return booking;
}

	@Override
	public List<Booking> getBookingsAtStationByEmployee(String campus, String city,String mailId) throws EvChargingException {
		List<Booking> bookings = bookingRepo.getBookingsAtStationByEmployee(campus, city, mailId);

		if (bookings.isEmpty()) {
			throw new EvChargingException("No such booking found");
		}

		return bookings;
	}

	@Override
	public List<Booking> cancelBooking(String ticketNo) throws EvChargingException {

		Booking booking = getBookingFromTicketNo(ticketNo);
		booking.setBookingByEmployee(null);
		booking.setBookingStatus(BookingStatus.CANCELLED);
		bookingRepo.saveAndFlush(booking);
		return bookingRepo.findAll();

	}

	@Override
	public List<Booking> getChargerDetailListForSlot(LocalDate forDate, LocalTime duration, String campus,String city) throws EvChargingException {
		List<Booking> chargerDetails = bookingRepo.getChargerDetailListForSlot(forDate, campus, city);
		if (chargerDetails.isEmpty()) {
			throw new EvChargingException("Charger details not found for the selected parameters");
		}

		return chargerDetails;

	}
	
	@Override
	public List<String> getChargersStations() {
		return chargerRepo.getChargingStations().stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Charger> addChargers(List<Charger> chargers) throws EvChargingException {
		for (Charger currentCharger : chargers) {
			try {
				List<Booking> bookings=createBookings(currentCharger);
				for (Booking booking : bookings) {
					bookingRepo.saveAndFlush(booking);
				}
				chargerRepo.saveAndFlush(currentCharger);
			} catch (Exception exception) {
				throw new EvChargingException(exception.getMessage());
			}
		}
		return chargerRepo.findAll();

	}

	@Override
	public List<Charger> removeCharger(String chargerId) throws EvChargingException {
		try {
			if (chargerRepo.findById(chargerId).isEmpty()) {
				throw new EvChargingException("Charger doesn't exist");
			}

			chargerRepo.deleteById(chargerId);
			chargerRepo.flush();
			return chargerRepo.findAll();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	public Date handleRemovalDate(String chargerId, Date removalDate) throws EvChargingException {
		Date currentDate = new Date();
		int result = currentDate.compareTo(removalDate);
		if (result == 0) {
			removeCharger(chargerId);
		}
		return currentDate;
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
	public List<Charger> modifyCharger(String chargerId, LocalTime newDuration, String[] newChargerActiveTimimgs)
			throws EvChargingException {
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
			if (optionalCharger.isEmpty()) {
				throw new EvChargingException("Charger doesn't exists");
			}
			Charger charger = optionalCharger.get();
			charger.setSlotDuration(newDuration);
			chargerRepo.save(charger);
			return chargerRepo.findAll();
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public Charger haltCharger(String chargerId, Date newStartDate) throws EvChargingException {
		return null;
	}

	@Override
	public List<Booking> getBookingsByJoin(Date fromDate, Date toDate, String stationId) {
		return null;
	}

	@Override
	public List<Booking> getBookingsDetail(String chargerId, Date fromDate, Date toDate) {
		return null;
	}

	@Override
	public List<Charger> getChargersFromCampusAndCity(String stationId) throws EvChargingException {
		return null;
	}
	
	public static List<Booking> createBookings(Charger charger) {
		List<Booking> bookings=new ArrayList<Booking>();
		for(LocalDate startDate=charger.getStartingDate(); startDate.isBefore(startDate.plusDays(10));startDate=startDate.plusDays(1)) {
			for(LocalTime startTime=charger.getStartTime(); startTime.isBefore(charger.getEndTime());startTime=startTime.plus(charger.getSlotDuration().getHour(),ChronoUnit.HOURS).plus(charger.getSlotDuration().getMinute(), ChronoUnit.MINUTES)) {
				Booking booking=new Booking();
				booking.setBookedCharger(charger);
				booking.setBookedDate(startDate);
				booking.setBookingStatus(BookingStatus.BOOKED);
				booking.setStartTime(startTime);
				booking.setEndTime(startTime.plus(charger.getSlotDuration().getHour(),ChronoUnit.HOURS).plus(charger.getSlotDuration().getMinute(), ChronoUnit.MINUTES));
				bookings.add(booking);
			}
		}
		return bookings;
		
	}

}
