/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.enums.BookingStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;

@Repository
public interface BookingDao extends JpaRepository<Booking, String> {

	@Query("select B from Booking B where B.bookedCharger.station.campus=:campus and B.bookedCharger.station.city=:city and B.bookingByEmployee.mailId=:mailId")
	public List<Booking> getBookingsAtStationByEmployee(@Param("campus") String campus,@Param("city") String city,@Param("mailId") String mailId);

	@Query("select B from Booking B where B.bookedDate>=:forDate and B.bookedCharger.chargerType=:selectedChargerType and B.bookedCharger.station.campus=:campus and B.bookedCharger.station.city=:city")
	public List<Booking> searchSlots(@Param("forDate") LocalDate forDate,@Param("selectedChargerType") ChargerType selectedChargerType, @Param("campus") String campus,@Param("city") String city);

	@Query("select B from Booking B where B.bookedDate=:forDate and B.bookedCharger.station.campus=:campus and B.bookedCharger.station.city=:city")
	public List<Booking> getChargerDetailListForSlot(@Param("forDate") LocalDate forDate,@Param("campus") String campus,@Param("city") String city);
	
	@Query("select B from Booking B where B.bookedDate=:bookedDate and B.startTime=:startTime and B.bookedCharger.chargerId=:chargerId")
	public Booking getBookingRowToBook(@Param("bookedDate")LocalDate bookedDate,@Param("startTime") LocalTime startTime,@Param("chargerId") String chargerId);
	
	@Query("select B from Booking B where B.bookedCharger.chargerId=:chargerId")
	public List<Booking> getBookingsForCharger(@Param("chargerId")String chargerId);

	@Query("select B from Booking B where B.bookingByEmployee.employeeId=:empId")
	public List<Booking> getAllBookingsByEmployee(@Param("empId")String empId);

	@Query("select B from Booking B where B.slotDuration=:slotDuration and B.bookedDate=:selectedDate and B.bookedCharger.station.stationId=:stationId")
	public List<Booking> getBookingsBySlotDuartion(@Param("selectedDate")LocalDate selectedDate,@Param("slotDuration") LocalTime slotDuration,@Param("stationId") String stationId);

	@Query("select new map(count(B.bookedCharger) as bookedMachineCount, B.bookedCharger as bookedMachine) from Booking B where B.bookedDate>=:fromDate and B.bookedDate<=:toDate and B.bookedCharger.station.stationId=:selectedStationId and B.bookingStatus=:status group by B.bookedCharger.chargerId")
	public List<Map<String, Object>> generateBookingsReportByJoin(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, @Param("selectedStationId") String selectedStationId,@Param("status") BookingStatus status);

	@Query("select B from Booking B where B.bookedCharger.chargerId=:chargerId and B.bookedDate>=:fromdate and B.bookedDate<=:todate")
	public List<Booking> getBookingsOfMachine(@Param("chargerId")String machineId,@Param("fromdate") LocalDate fromDate,@Param("todate") LocalDate toDate);
	
	@Query("select B from Booking B where B.bookedDate=:today and B.bookedCharger.chargerId=:chargerId")
	public List<Booking> getBookingRowToDate(@Param("today")LocalDate today,@Param("chargerId") String chargerId);

}
