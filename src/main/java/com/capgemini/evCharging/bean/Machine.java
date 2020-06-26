package com.capgemini.evCharging.bean;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.MachineStatus;
import com.capgemini.evCharging.bean.enums.MachineType;
import com.capgemini.evCharging.bean.enums.SlotDuration;



@Entity
public class Machine implements Serializable{
	
	

	private static final long serialVersionUID = 1L;

	
	@Id
	private Integer machineId;
	
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")

	@GenericGenerator(

			name = "machine_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "M"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String machineName;
	
	@Enumerated(EnumType.STRING)
	private MachineType machineType;
	
	@Enumerated(EnumType.STRING)
	private MachineStatus machineStatus; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Station machineStation;
	
	@Enumerated(EnumType.STRING)
	private SlotDuration slotDuration; 
	
	private Date startingDate;
	
	private LocalTime startTime;
	private LocalTime endTime;
	
	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public MachineType getMachineType() {
		return machineType;
	}

	public void setMachineType(MachineType MachineType) {
		this.machineType = MachineType;
	}

	public MachineStatus getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(MachineStatus machineStatus) {
		this.machineStatus = machineStatus;
	}

	public Station getMachineStation() {
		return machineStation;
	}

	public void setMachineStation(Station machineStation) {
		this.machineStation = machineStation;
	}

	public SlotDuration getSlotDuration() {
		return slotDuration;
	}

	public void setSlotDuration(SlotDuration slotDuration) {
		this.slotDuration = slotDuration;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	@Override
	public String toString() {
		return "Machine [machineId=" + machineId + ", machineName=" + machineName + ", machineType=" + machineType
				+ ", machineStatus=" + machineStatus + ", machineStation=" + machineStation + ", slotDuration="
				+ slotDuration + ", startingDate=" + startingDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}
	
	
	

}
