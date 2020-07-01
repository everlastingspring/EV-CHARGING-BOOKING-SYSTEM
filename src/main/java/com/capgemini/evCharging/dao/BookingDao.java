package com.capgemini.evCharging.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.enums.ChargerType;

@Repository
public interface BookingDao extends JpaRepository<Booking, String> {

	@Query("select B from Booking B where B.bookedCharger.station.campus=:campus and B.bookedCharger.station.city=:city and B.bookingByEmployee.mailId=:mailId")
	public List<Booking> getBookingsAtStationByEmployee(@Param("campus") String campus,@Param("city") String city,@Param("mailId") String mailId);

	@Query("select B from Booking B where B.bookedDate=:forDate and B.bookedCharger.chargerType=:selectedChargerType and B.bookedCharger.station.campus=:campus and B.bookedCharger.station.city=:city")
	public List<Booking> searchSlots(@Param("forDate") LocalDate forDate,@Param("selectedChargerType") ChargerType selectedChargerType, @Param("campus") String campus,@Param("city") String city);

	@Query("select B from Booking B where B.bookedDate=:forDate and B.bookedCharger.station.campus=:campus and B.bookedCharger.station.city=:city")
	public List<Booking> getChargerDetailListForSlot(@Param("forDate") LocalDate forDate,@Param("campus") String campus,@Param("city") String city);
	
	@Query("select B from Booking B where B.bookedDate=:bookedDate and B.startTime=:startTime and B.bookedCharger.chargerId=:chargerId")
	public Booking getBookingRowToBook(@Param("bookedDate")LocalDate bookedDate,@Param("startTime") LocalTime startTime,@Param("chargerId") String chargerId);

	
}
