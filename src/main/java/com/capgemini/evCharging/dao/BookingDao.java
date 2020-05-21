package com.capgemini.evCharging.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Booking;


@Repository
public interface BookingDao extends JpaRepository<Booking, String>{
	
	//jql
	@Query("select B from Booking B where B.bookedCharger.chargerStation.stationId=:stationId and B.bookingByEmployee.mailId=:mailId")
	public List<Booking> getBookings(@Param("stationId") String stationId,@Param("mailId") String mailId);

}
