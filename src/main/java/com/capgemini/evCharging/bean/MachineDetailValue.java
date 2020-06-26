package com.capgemini.evCharging.bean;

import com.capgemini.evCharging.bean.enums.MachineDetailStatus;



//Non Entity class

public class MachineDetailValue {
	private Integer machineId;
	private Integer bookedByEmployeeId;
	private MachineDetailStatus status = MachineDetailStatus.FREE;

	public MachineDetailValue(Integer machineId) {
		this.machineId = machineId;
	}

	public MachineDetailValue(Integer machineId, Integer bookedByEmployeeId,MachineDetailStatus status) {
		this.machineId = machineId;
		this.bookedByEmployeeId = bookedByEmployeeId;
		this.status = status;

	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public Integer getBookedByEmployeeId() {
		return bookedByEmployeeId;
	}

	public void setBookedByEmployeeId(Integer bookedByEmployeeId) {
		this.bookedByEmployeeId = bookedByEmployeeId;
	}

	public MachineDetailStatus getStatus() {
		return status;
	}

	public void setStatus(MachineDetailStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MachineDetailValue [machineId=" + machineId + ", bookedByEmployeeId=" + bookedByEmployeeId + ", status="
				+ status + "]";
	}

}

