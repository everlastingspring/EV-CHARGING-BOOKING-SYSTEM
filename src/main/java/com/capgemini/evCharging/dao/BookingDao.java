package com.capgemini.evCharging.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;

@Repository
public interface BookingDao extends JpaRepository<Booking, String> {

	@Query("select B from Booking B where B.bookedCharger.chargerStation.stationId=:stationId and B.bookingByEmployee.mailId=:mailId")
	public List<Booking> getBookingsAtStationByEmployee(@Param("stationId") String stationId,@Param("mailId") String mailId);

	@Query("select B from Booking B where B.bookedDate=:forDate and B.bookedCharger.chargerType=:selectedChargerType and B.bookedCharger.campus=:campus and B.bookedCharger.city=:city")
	public List<Booking> getChargersDetaiListForType(@Param("forDate") Date forDate,@Param("selectedChargerType") ChargerType selectedChargerType, @Param("campus") String campus,@Param("city") String city);

	@Query("select B from Booking B where B.bookedDate=:forDate and B.slotDuration=:duration and B.bookedCharger.campus=:campus and B.bookedCharger.city=:city")
	public List<Booking> getChargerDetailListForSlot(@Param("forDate") Date forDate,@Param("duration") SlotDuration duration, @Param("campus") String campus,@Param("city") String city);
	
	@Query("select B from Booking B where B.bookedDate=:bookedDate and B.bookedTiming=:bookedTiming and B.bookedCharger.chargerId=:chargerId")
	public Booking getBookingForDateTime(@Param("bookedDate")Date bookedDate,@Param("bookedTiming") String bookedTiming,@Param("chargerId") String chargerId);

	
}
