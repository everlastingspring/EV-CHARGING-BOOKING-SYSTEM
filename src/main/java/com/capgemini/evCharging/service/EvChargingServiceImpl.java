package com.capgemini.evCharging.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.Credential;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.ReportFormat;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.BookingStatus;
import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.dao.BookingDao;
import com.capgemini.evCharging.dao.ChargerDao;
import com.capgemini.evCharging.dao.CredentialDao;
import com.capgemini.evCharging.dao.EmployeeDao;
import com.capgemini.evCharging.dao.StationDao;
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
	
	@Autowired
	StationDao stationDao;

	@Override
	public Boolean areCredentialsMatched(String mailId, String password) throws EvChargingException {

		try {
			Optional<Credential> optionalCredential = credentialRepo.getAccountFromMailId(mailId);
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
			employeeRepo.saveAndFlush(emp);
			Employee employee=employeeRepo.getEmployeeFromMailid(emp.getMailId());
			credential.setEmployeeId(employee.getEmployeeId());
			credential.setMailId(employee.getMailId());
			credential.setPassword(hashedPassword);
			credential.setSalt(salt);
			credentialRepo.saveAndFlush(credential);

			return true;
		} catch (Exception exception) {

			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public Boolean registerAdmin(Employee emp) throws EvChargingException {
		try {
		Credential credential = new Credential();
		byte[] salt = HashAlgorithmService.createSalt();
		String hashedPassword = HashAlgorithmService.hashedPassword(emp.getPassword(), salt);
		employeeRepo.saveAndFlush(emp);
		Employee employee=employeeRepo.getEmployeeFromMailid(emp.getMailId());
		credential.setEmployeeId(employee.getEmployeeId());
		credential.setMailId(employee.getMailId());
		credential.setIsAdmin(true);
		credential.setPassword(hashedPassword);
		credential.setSalt(salt);
		credentialRepo.saveAndFlush(credential);

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
 
	@Override
	public Booking bookCharger(LocalDate bookedDate, LocalTime startTime, String chargerId, String employeeId) throws EvChargingException {
		
		
		Booking booking = bookingRepo.getBookingRowToBook(bookedDate, startTime, chargerId);
		booking.setBookingByEmployee(employeeRepo.findById(employeeId).get());
		booking.setBookingStatus(BookingStatus.BOOKED);
		bookingRepo.saveAndFlush(booking);
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
	public Boolean cancelBooking(String ticketNo) throws EvChargingException {
		
		Optional<Booking> optional=bookingRepo.findById(ticketNo);
		if(optional.isEmpty()) {
			throw new EvChargingException("booking with id "+ticketNo +" does not exist");
		}
		Booking booking =optional.get();
		if(!booking.getStartTime().isAfter(LocalTime.now().plusHours(3))) {
			throw new EvChargingException("you can cancel the booking only 3 hours before the bookedtime");
		}
		booking.setBookingByEmployee(null);
		booking.setBookingStatus(BookingStatus.CANCELLED);
		bookingRepo.saveAndFlush(booking);
		return true;

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
	public List<Station> getChargersStations() {
		return stationDao.getChargingStations();
	}

	@Override
	public List<Charger> addChargers(List<Charger> chargers) throws EvChargingException {
		for (Charger currentCharger : chargers) {
			Optional<Station> station=stationDao.checkIfStationExists(currentCharger.getStation().getCity(), currentCharger.getStation().getCampus());
			if(station.isPresent()) {
				currentCharger.setStation(station.get());
				chargerRepo.save(currentCharger);
			}else {
				stationDao.save(currentCharger.getStation());
				chargerRepo.save(currentCharger);
			}
			
			try {
				List<Booking> bookings=createBookings(currentCharger);
				for (Booking booking : bookings) {
					bookingRepo.saveAndFlush(booking);
				}
				chargerRepo.flush();
				
			} catch (Exception exception) {
				throw new EvChargingException(exception.getMessage());
			}
		}
		
		return chargerRepo.findAll();

	}

	@Override
	public Boolean removeCharger(String chargerId) throws EvChargingException {
		try {
			Optional<Charger> optional=chargerRepo.findById(chargerId);
			if (optional.isEmpty() || optional.get().getChargerStatus().equals(ChargerStatus.REMOVED)) {
				throw new EvChargingException("Charger "+chargerId+" doesn't exist");
			}
			optional.get().setChargerStatus(ChargerStatus.REMOVED);
			chargerRepo.save(optional.get());
			bookingRepo.deleteAll(bookingRepo.getBookingsForCharger(chargerId).stream().
					filter(charger->charger.getBookedDate().isEqual(LocalDate.now()) || charger.getBookedDate().isAfter(LocalDate.now())).
					filter(charger->!charger.getBookingStatus().equals(BookingStatus.BOOKED)).collect(Collectors.toList()));
			return true;
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public Charger resumeCharger(String chargerId) throws EvChargingException {
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
			if (optionalCharger.isEmpty() || !optionalCharger.get().getChargerStatus().equals(ChargerStatus.HALTED)) {
				throw new EvChargingException("charger you selected is either does not exist or not halted");
			}
			Charger charger = optionalCharger.get();
			if(charger.getStartingDate().isEqual(LocalDate.now().plusDays(1))) {
				charger.setChargerStatus(ChargerStatus.ACTIVE);
				chargerRepo.save(charger);
				bookingRepo.saveAll(bookingRepo.getBookingsForCharger(chargerId).stream().
						filter(booking->booking.getBookedDate().isEqual(LocalDate.now().plusDays(1)) || booking.getBookedDate().isAfter(LocalDate.now().plusDays(1))).
						map(boo->{boo.setBookingStatus(BookingStatus.AVAILABLE);return boo;}).collect(Collectors.toList()));
			}
			
			return charger;
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}

	}

	@Override
	public Charger modifyCharger(Charger charger) throws EvChargingException {
		try {
			Optional<Charger> optionalCharger = chargerRepo.findById(charger.getChargerId());
			if (optionalCharger.isEmpty() || !optionalCharger.get().getChargerStatus().equals(ChargerStatus.ACTIVE)) {
				throw new EvChargingException("Charger doesn't exists");
			}
			Charger charger1 = optionalCharger.get();
			charger1.setStartTime(charger.getStartTime());
			charger1.setEndTime(charger.getEndTime());
			charger1.setSlotDuration(charger.getSlotDuration());
			charger1.setStartingDate(charger.getStartingDate());
			chargerRepo.save(charger1);
			bookingRepo.deleteAll(bookingRepo.getBookingsForCharger(charger1.getChargerId()).stream().
					filter(charger2->charger2.getBookedDate().isEqual(charger.getStartingDate()) || charger2.getBookedDate().isAfter(charger.getStartingDate())).
					collect(Collectors.toList()));
			List<Booking> bookings=createBookings(charger1);
			for (Booking booking : bookings) {
				bookingRepo.saveAndFlush(booking);
			}
			return charger1;
		} catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}

	@Override
	public Charger haltCharger(String chargerId, LocalDate newStartDate) throws EvChargingException {
		try {
			Optional<Charger> optional=chargerRepo.findById(chargerId);
			if(optional.isEmpty() || optional.get().getChargerStatus().equals(ChargerStatus.REMOVED)) {
				throw new EvChargingException("no charger with id "+chargerId+" exists");
			}
			Charger charger=optional.get();
			charger.setStartingDate(newStartDate);
			charger.setChargerStatus(ChargerStatus.HALTED);
			chargerRepo.save(charger);
			
			bookingRepo.saveAll(bookingRepo.getBookingsForCharger(chargerId).stream().
		filter(booking->booking.getBookedDate().isEqual(LocalDate.now()) || booking.getBookedDate().isAfter(LocalDate.now())).
		map(boo->{boo.setBookingStatus(BookingStatus.NOT_AVAILABLE);return boo;}).collect(Collectors.toList()));
			return charger;
		}
		catch (Exception exception) {
			throw new EvChargingException(exception.getMessage());
		}
	}
	
	public static List<Booking> createBookings(Charger charger) {
		List<Booking> bookings=new ArrayList<Booking>();
		for(LocalDate startDate=charger.getStartingDate(); startDate.isBefore(charger.getStartingDate().plusDays(10));startDate=startDate.plusDays(1)) {
			for(LocalTime startTime=charger.getStartTime(); startTime.isBefore(charger.getEndTime());startTime=startTime.plusMinutes(charger.getSlotDuration().get(ChronoField.MINUTE_OF_DAY))) {
				Booking booking=new Booking();
				booking.setBookedCharger(charger);
				booking.setBookedDate(startDate);
				booking.setBookingStatus(BookingStatus.AVAILABLE);
				booking.setSlotDuration(charger.getSlotDuration());
				booking.setStartTime(startTime);
				booking.setEndTime(startTime.plusMinutes(charger.getSlotDuration().get(ChronoField.MINUTE_OF_DAY)));
				bookings.add(booking);
			}
		}
		return bookings;
		
	}

	@Override
	public Charger haltMachine(String chargerId, LocalDate newStartDate, LocalTime newStartTime, LocalTime newEndTime)
			throws EvChargingException {
		if(!chargerRepo.existsById(chargerId)) {
		throw new EvChargingException("Charger with " + chargerId + " doesn't exist");
	}
	Charger charger=chargerRepo.findById(chargerId).get();
	charger.setStartingDate(newStartDate);
	charger.setStartTime(newStartTime);
	charger.setEndTime(newEndTime);
	charger.setChargerStatus(ChargerStatus.HALTED);
	bookingRepo.saveAll(bookingRepo.getBookingsForCharger(chargerId).stream().
			filter(booking->booking.getBookedDate().isEqual(LocalDate.now()) || booking.getBookedDate().isAfter(LocalDate.now())).
			map(boo->{boo.setBookingStatus(BookingStatus.NOT_AVAILABLE);return boo;}).collect(Collectors.toList()));
	chargerRepo.save(charger);
	return charger;
	}

	@Override
	public List<Booking> getEmployeeAllBookings(String empId) throws EvChargingException {
		List<Booking> bookings = bookingRepo.getAllBookingsByEmployee(empId);

		if(bookings.isEmpty()) {
			throw new EvChargingException("User with id " + empId + " has no booking");
		}

		return bookings;
	}

	@Override
	public List<Booking> getEmployeeCurrentBookings(String empId) throws EvChargingException {
		return getEmployeeAllBookings(empId).stream().
		filter(booking->(booking.getBookedDate().isEqual(LocalDate.now()) && booking.getStartTime().isAfter(LocalTime.now())) || booking.getBookedDate().isAfter(LocalDate.now())).
		collect(Collectors.toList());
	}

	@Override
	public List<Booking> rescheduleBooking(String rescheduleTicketNo, LocalDate rescheduledBookedDate,
			LocalTime rescheduledBookingStartTiming, String chargerId, String employeeId) throws EvChargingException {
		Booking entity=bookingRepo.findById(rescheduleTicketNo).get();
		entity.setBookingStatus(BookingStatus.CANCELLED);
		bookingRepo.save(entity);
		Booking booking=bookCharger(rescheduledBookedDate, rescheduledBookingStartTiming, chargerId, employeeId);
		bookingRepo.save(booking);
		bookingRepo.flush();
		return getEmployeeAllBookings(employeeId);
	}

	@Override
	public List<Booking> getBookingsBySlotDuartion(LocalDate selectedDate, LocalTime selectedSlotDuration,
			String stationId) throws EvChargingException {
		return bookingRepo.getBookingsBySlotDuartion(selectedDate,selectedSlotDuration,stationId);
		 
	}

	@Override
	public List<ReportFormat> generateBookingsReport(String stationId, LocalDate fromDate, LocalDate toDate)
			throws EvChargingException {
		List<Map<String,Object>> reportList =  bookingRepo.generateBookingsReportByJoin(fromDate, toDate, stationId);
		List<ReportFormat> reports = new ArrayList<ReportFormat>();
		for(Map<String,Object> _report : reportList) {
			ReportFormat report = new ReportFormat();
			report.setBookingsCount( (Long)_report.get("bookedMachineCount") );
			report.setBookedMachine( (Charger)_report.get("bookedMachine"));
			reports.add(report);
		}
		return reports;
	}

	@Override
	public List<Booking> generateMachineBookingsReport(String machineId, LocalDate fromDate, LocalDate toDate)
			throws EvChargingException {
		List<Booking> bookings = bookingRepo.getBookingsOfMachine(machineId, fromDate, toDate);
		return bookings;
	}

	@Override
	public List<Station> addStation(String city, String campusLocation) throws EvChargingException {
		Station newStation = new Station();
		newStation.setCity(city);
		newStation.setCampus(campusLocation);
		stationDao.save(newStation);
		return stationDao.findAll();
	}

}
