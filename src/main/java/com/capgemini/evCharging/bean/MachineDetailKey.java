package com.capgemini.evCharging.bean;

import java.time.LocalTime;

import com.capgemini.evCharging.bean.enums.SlotDuration;


//Non Entity class
public class MachineDetailKey {

	
	private LocalTime startTime;
	private LocalTime endTime;
	private SlotDuration slotDuration;
	
	
	public MachineDetailKey(LocalTime startTime, LocalTime endTime,SlotDuration slotDuration) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotDuration = slotDuration;
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
	public SlotDuration getSlotDuration() {
		return slotDuration;
	}
	public void setSlotDuration(SlotDuration slotDuration) {
		this.slotDuration = slotDuration;
	}
	@Override
	public String toString() {
		return "MachineDetailKey [startTime=" + startTime + ", endTime=" + endTime + ", slotDuration=" + slotDuration
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((slotDuration == null) ? 0 : slotDuration.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MachineDetailKey other = (MachineDetailKey) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (slotDuration != other.slotDuration)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
	
	
	
}
