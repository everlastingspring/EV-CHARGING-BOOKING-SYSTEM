package com.capgemini.evCharging.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.enums.BookingStatus;


@Repository
public interface BookingDao extends JpaRepository<Booking, Integer>{
	
	//hql
	@Query("select B from Booking B where B.bookedMachine.machineStation.stationId=:stationId and B.bookingByEmployee.employeeId=:employeeId")
	public List<Booking> getAllBookingsAtStationByEmployee(@Param("stationId") Integer stationId,@Param("employeeId") Integer employeeId);
	
	@Query("select B from Booking B where B.bookedMachine.machineStation.stationId=:stationId and B.bookingByEmployee.employeeId=:employeeId and B.bookingStartTime>=:currentTime and B.bookedDate>=:currentDate")
	public List<Booking> getCurrentBookingsAtStationByEmployee(@Param("stationId") Integer stationId,@Param("employeeId") Integer employeeId,@Param("currentDate") String currentDate,@Param("currentTime") String currentTime);
	
	
	@Query("select B from Booking B where B.bookedMachine.machineId=:machineId and B.bookedDate=:selectedDate and B.status=:status")
	public List<Booking> getBookingsOfMachine(@Param("machineId") Integer machineId, @Param("selectedDate")Date selectedDate,@Param("status") BookingStatus status);

}
