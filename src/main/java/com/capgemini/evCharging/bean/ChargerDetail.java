package com.capgemini.evCharging.bean;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.capgemini.evCharging.bean.enums.ChargerDetailStatus;
import com.capgemini.evCharging.bean.enums.SlotDuration;

@Entity
public class ChargerDetail {
	

	@EmbeddedId
	ChargerDetailId detailId;
	
	@Enumerated(EnumType.STRING)
	private SlotDuration chargerSlotDuration;
	
	@Enumerated(EnumType.STRING)
	private ChargerDetailStatus chargerDetailStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee bookedByEmployee;

	

	public ChargerDetailId getDetailId() {
		return detailId;
	}

	public void setDetailId(ChargerDetailId detailId) {
		this.detailId = detailId;
	}

	public SlotDuration getChargerSlotDuration() {
		return chargerSlotDuration;
	}

	public void setChargerSlotDuration(SlotDuration chargerSlotDuration) {
		this.chargerSlotDuration = chargerSlotDuration;
	}

	public ChargerDetailStatus getChargerDetailStatus() {
		return chargerDetailStatus;
	}

	public void setChargerDetailStatus(ChargerDetailStatus chargerDetailStatus) {
		this.chargerDetailStatus = chargerDetailStatus;
	}

	public Employee getBookedByEmployee() {
		return bookedByEmployee;
	}

	public void setBookedByEmployee(Employee bookedByEmployee) {
		this.bookedByEmployee = bookedByEmployee;
	}

	@Override
	public String toString() {
		return "ChargerDetail [detailId=" + detailId + ", chargerSlotDuration=" + chargerSlotDuration
				+ ", chargerDetailStatus=" + chargerDetailStatus + ", bookedByEmployee=" + bookedByEmployee + "]";
	}

	
	
	
	

}

