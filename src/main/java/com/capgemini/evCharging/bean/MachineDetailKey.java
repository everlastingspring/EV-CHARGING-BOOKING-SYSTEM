package com.capgemini.evCharging.bean;

import java.time.LocalTime;


//Non Entity class
public class MachineDetailKey {

	
	private LocalTime startTime;
	private LocalTime endTime;
	
	
	public MachineDetailKey(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
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
	@Override
	public String toString() {
		return "MachineDetailKey [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
