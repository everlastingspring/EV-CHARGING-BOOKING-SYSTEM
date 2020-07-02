package com.capgemini.evCharging.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Machine;
import com.capgemini.evCharging.bean.enums.MachineStatus;
import com.capgemini.evCharging.bean.enums.MachineType;
import com.capgemini.evCharging.bean.enums.SlotDuration;
@Repository
public interface MachineDao extends JpaRepository<Machine, Integer>{
	
	@Query("select M from Machine M where M.machineType=:level")
	public List<Machine> getMachinesByLevel(@Param("level") String level);
	

	@Query("select M from Machine M where M.machineType=:selectedMachineType and M.machineStation.stationId=:stationId and M.machineStatus=:machineStatus and M.startingDate<=:selectedDate")
	public List<Machine> getActiveMachinesOfStationAndType(@Param("selectedMachineType") MachineType selectedMachineType,@Param("stationId") Integer stationId,@Param("machineStatus")MachineStatus machineStatus, @Param("selectedDate") Date selectedDate);
	
	
	@Query("select M from Machine M where M.slotDuration=:selectedSlotDuration and M.machineStation.stationId=:stationId and M.machineStatus=:machineStatus and M.startingDate<=:selectedDate")
	public List<Machine> getActiveMachinesOfStationAndDuration(@Param("selectedSlotDuration") SlotDuration selectedSlotDuration,@Param("stationId") Integer stationId,@Param("machineStatus")MachineStatus machineStatus, @Param("selectedDate") Date selectedDate);
	
	@Query("select M from Machine M where M.machineStation.stationId=:stationId")
	public List<Machine> getMachinesOfStation(@Param("stationId") Integer stationId);
	
	@Query("select M from Machine M where M.startingDate=:currentDate and M.machineStatus=:machineHaltStatus")
	public List<Machine> getMachinesWhichCanResume(@Param("currentDate") Date currentDate, @Param("machineHaltStatus") MachineStatus machineHaltStatus);
	
	
	//select * from machine where machine.machineType = 'Level1' and machine.stationId = stationId and machine.duration = duration and machine.machine_status = 'Active' and machine.staring_date <= currentDate;
	
	
}
