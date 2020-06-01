package com.capgemini.evCharging.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.evCharging.bean.Admin;
import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.ChargerDetail;
import com.capgemini.evCharging.bean.ChargerDetailId;
import com.capgemini.evCharging.bean.Credential;
import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.bean.Station;
import com.capgemini.evCharging.bean.enums.ChargerDetailStatus;
import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
import com.capgemini.evCharging.dao.AdminDao;
import com.capgemini.evCharging.dao.BookingDao;
import com.capgemini.evCharging.dao.ChargerDao;
import com.capgemini.evCharging.dao.ChargerDetailDao;
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
	
	@Autowired
	ChargerDetailDao chargerDetailRepo;

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
	public Boolean registerAdmin(Admin admin, String password) throws EvChargingException {
		try {
			Credential credential = new Credential();
			credential.setMailId(admin.getMailId());
			byte[] salt = HashAlgorithmService.createSalt();
			String hashedPassword = HashAlgorithmService.hashedPassword(password, salt);
			credential.setHashedPassword(hashedPassword);
			credential.setSaltArray(salt);
			credentialRepo.save(credential);

			adminRepo.save(admin);

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
	public List<Charger> getChargersOfStation(String stationId) throws EvChargingException {
		List<Charger> chargers = chargerRepo.getChargersOfStation(stationId);
		if(chargers.isEmpty())  {
			throw new EvChargingException("Charging Station doesn't exist"); 
		}
		return chargers;
	}

	@Override
	public List<Charger> getChargersOfStationAndType(String stationId, ChargerType chargerType) throws EvChargingException {
		List<Charger> chargers = chargerRepo.getChargersOfStationAndType(chargerType, stationId);
		if(chargers.isEmpty())  {
			throw new EvChargingException("Charging Station doesn't exist"); 
		}
		return chargers;
	}

	@Override
	public Date getNextAvailableBookingDate(ChargerType selectedChargerType, String selectedStationId) {
		// Deals with ChargerDetails table.
		
		return new Date();
	}

	@Override
	public List<ChargerDetail> getChargerDetailListForType(Date forDate, ChargerType selectedChargerType, String selectedStationId) throws EvChargingException {
		List<ChargerDetail> chargerDetails =  chargerDetailRepo.getChargersDetaiListForType(1212, selectedChargerType, selectedStationId);
		
		if(chargerDetails.isEmpty())  {
			throw new EvChargingException("Charging Station or Charger Type or selected date doesn't have any data"); 
		}
		return chargerDetails;
	}

	
	
	//Utility functions
	public Charger getChargerFromChargerId(String chargerId) throws EvChargingException {
		Optional<Charger> optionalCharger = chargerRepo.findById(chargerId);
		if(optionalCharger.isEmpty()) {
			throw new EvChargingException("Charger with chargerId " +  chargerId + "doesn't exist");
		}
		return optionalCharger.get();
	}
	
	public ChargerDetail getChargerDetailFromDetailId(ChargerDetailId detailId) throws EvChargingException {
		Optional<ChargerDetail> optionalChargerDetail = chargerDetailRepo.findById(detailId);
		if(optionalChargerDetail.isEmpty()) {
			throw new EvChargingException("Either the charger is in halt or has non-acive timings");
		} 
		return optionalChargerDetail.get();
	}
	
	public Employee getEmployeeDetailFromMailId(String mailId) throws EvChargingException {
		Optional<Employee> optionalEmployeeObj = employeeRepo.findById(mailId);
		if(optionalEmployeeObj.isEmpty()) {
			throw new EvChargingException("Account with" + mailId + " doesn't exist for Employee");
		} 
		return optionalEmployeeObj.get();
	}
	
	public Booking getBookingFromTicketNo(String ticketNo) throws EvChargingException {
		Optional<Booking> optionalBooking = bookingRepo.findById(ticketNo);
		if(optionalBooking.isEmpty()) {
			throw new EvChargingException("No such booking with " + ticketNo + " found");
		}
		
		return optionalBooking.get();
	}
	
	public Station getStationFromStationId(String stationId) throws EvChargingException {
		
		Optional<Station> optionalStation = stationRepo.findById(stationId);
		if(optionalStation.isEmpty()) {
			throw new EvChargingException("Station with stationId " +  stationId + "doesn't exist");
		}
		return optionalStation.get();
	}
	
	
	@Override
	public Booking bookCharger(Date bookedDate, String bookedTiming, String chargerId, String mailId) throws EvChargingException {
		
		ChargerDetailId detailId = new ChargerDetailId();
		Charger bookedCharger = getChargerFromChargerId(chargerId);
		detailId.setCharger(bookedCharger);
		detailId.setDetailForDate(bookedDate);
		detailId.setDetailFortime(bookedTiming);
		
		ChargerDetail chargerDetail = getChargerDetailFromDetailId(detailId);
		chargerDetail.setChargerDetailStatus(ChargerDetailStatus.BOOKED);
		Employee bookedbyEmployee =getEmployeeDetailFromMailId(mailId);
		chargerDetail.setBookedByEmployee(bookedbyEmployee);
		chargerDetailRepo.save(chargerDetail);
		
		Booking booking = new Booking();
		booking.setBookedCharger(bookedCharger);
		booking.setBookedDate(bookedDate);
		booking.setBookedForMins(bookedCharger.getSlotDuration());
		booking.setBookedTiming(bookedTiming);
		booking.setBookingByEmployee(bookedbyEmployee);
		bookingRepo.save(booking);
		
		return booking;
		
	
	}

	@Override
	public List<Booking> getBookings(String stationId, String mailId) throws EvChargingException {
		List<Booking> bookings = bookingRepo.getBookingsAtStationByEmployee(stationId,mailId);
		
		if(bookings.isEmpty()) {
			throw new EvChargingException("No such booking found");
		}
		
		return bookings;
	}

	@Override
	public List<Booking> cancelBooking(String ticketNo) throws EvChargingException {
		
		Booking booking = getBookingFromTicketNo(ticketNo);
		
		ChargerDetailId chargerDetailId = new ChargerDetailId();
		chargerDetailId.setCharger(booking.getBookedCharger());
		chargerDetailId.setDetailForDate(booking.getBookedDate());
		chargerDetailId.setDetailFortime(booking.getBookedTiming());
		
		ChargerDetail chargerDetail = getChargerDetailFromDetailId(chargerDetailId);
		chargerDetail.setBookedByEmployee(null);
		chargerDetail.setChargerDetailStatus(ChargerDetailStatus.FREE);
		chargerDetailRepo.save(chargerDetail);
		
		bookingRepo.deleteById(ticketNo);
		return bookingRepo.findAll();
		
	}

	@Override
	public List<ChargerDetail> getChargerDetailListForSlot(Date forDate, SlotDuration duration, String stationId) throws EvChargingException {
		
		List<ChargerDetail> chargerDetails = chargerDetailRepo.getChargerDetailListForSlot(1212, duration, stationId);
		if(chargerDetails.isEmpty()) {
			throw new EvChargingException("Charger details not found for the selected parameters");
		} 
		
		return chargerDetails;
		
	}

	@Override
	public List<Charger> addChargers(String stationId, List<Charger> chargers) throws EvChargingException {
		
		Station station = getStationFromStationId(stationId);
//		Charger
		for (Charger charger : chargers) {
			charger.setChargerStation(station);
			charger.setChargerStatus(ChargerStatus.ACTIVE);
			chargerRepo.save(charger);
		}
		
		//Update the ChargerDetails table
		
		addDetailstoChargerDetails(chargerRepo.findAll(), 30);
		return chargerRepo.findAll();
		
	}
	
//	public Date getFurthestDetailOfChargerDetail() {
//		chargerDetailRepo.
//	}
	
	public void addDetailstoChargerDetails(List<Charger> chargers, int chargerDetailDaysLimit) throws EvChargingException {
		
		Date currentDate = new Date();
		
		
		for (Charger charger : chargers) {
			
			
			int slotDuration = charger.getSlotDuration().getValue();
			
			for(int day = 0; day < chargerDetailDaysLimit; day++) {
				
				
				for (String timing : charger.getChargerActiveTimings()) {
					
					
					List<Integer> timeArray = splitStringArrayToMinArray(timing);
					
					
					for(int time = timeArray.get(0) ; time < timeArray.get(1) ; time+= slotDuration) {
						  int hours = time / 60;
						  int remMins = time - (hours*60);
						  
						  ChargerDetailId detailId = new ChargerDetailId();
						  detailId.setCharger(charger);
						  detailId.setDetailForDate(addDaysToDate(currentDate, day));
						  detailId.setDetailFortime(getTimeStringFormat(hours, remMins));
						  
						  Optional<ChargerDetail> optionalChargerDetail = chargerDetailRepo.findById(detailId);
						  if(optionalChargerDetail.isPresent()) {
							  throw new EvChargingException("ChargerDetail already exists");
						  }
						  
						  ChargerDetail chargerDetail = new ChargerDetail();
						  chargerDetail.setDetailId(detailId);
						  chargerDetail.setChargerDetailStatus(ChargerDetailStatus.FREE);
						  chargerDetail.setBookedByEmployee(null);
						  chargerDetail.setChargerSlotDuration(charger.getSlotDuration());
						  chargerDetailRepo.save(chargerDetail);
					}
				}
				
			}
		}

		
	}	
	
	
	
	
	private Date addDaysToDate(Date date, int days) {
		
		Calendar c = Calendar.getInstance();
        c.setTime(date);
 
        c.add(Calendar.DAY_OF_MONTH, days);
       
        Date currentDatePlusDays = c.getTime();
        return currentDatePlusDays;
	}
	
	public static List<Integer> splitStringArrayToMinArray(String activeTimingsInArray) {
		
		//convert "10:30-11:00" to [10.5*60, 12*60]
		List<Integer> timeArray = new ArrayList<Integer>();
		
		String[] splitActiveTimingArray = activeTimingsInArray.split("-");
		for (String time : splitActiveTimingArray) {
			
			String[] splitActiveTimingInStringFormat = time.split(":");
			Integer activeTimeInMins = Integer.parseInt(splitActiveTimingInStringFormat[0]) * 60 + Integer.parseInt(splitActiveTimingInStringFormat[1]);
			timeArray.add(activeTimeInMins);
		}
		return timeArray; 
	}
	
	public static Integer getActiveMins(String activeTimingInStringFormat) {
			Integer mins = 0;
	
			List<Integer> timeInHours = splitStringArrayToMinArray(activeTimingInStringFormat);
			mins += (timeInHours.get(1) - timeInHours.get(0));
			return mins;
		
	}
	
	private static String getTimeStringFormat(Integer hours, Integer remMins) {
		String hourString = hours.toString();
		String minsString = remMins.toString();
		hourString  = hourString.length() == 1 ? "0".concat(hourString) : hourString;
		
		minsString  = minsString.length() == 1 ? minsString.concat("0") : minsString;
		return (hourString + ":" +  minsString);
				
	}
	
	
	@Override
	public List<Charger> removeCharger(String chargerId, Date removalDate) throws EvChargingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Charger haltCharger(String chargerId, Date newStartDate) throws EvChargingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Charger resumeCharger(String chargerId) throws EvChargingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Charger> modifyCharger(String chargerId, SlotDuration newDuration, String[] newChargerActiveTimimgs)
			throws EvChargingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getBookingsByJoin(Date fromDate, Date toDate, String stationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getBookingsDetail(String chargerId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}



}



/* 	@Override
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
public List<Charger> removeCharger(String chargerId, Date removalDate) throws EvChargingException {
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
} */