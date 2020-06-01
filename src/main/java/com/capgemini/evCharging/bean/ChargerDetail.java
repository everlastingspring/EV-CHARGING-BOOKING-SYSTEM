package com.capgemini.evCharging.bean;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.capgemini.evCharging.bean.enums.ChargerDetailStatus;
import com.capgemini.evCharging.bean.enums.SlotDuration;

@Entity
public class ChargerDetail {
	
	
	@Id
	Integer detailId;
	
	@Enumerated(EnumType.STRING)
	private SlotDuration chargerSlotDuration;
	
	@Enumerated(EnumType.STRING)
	private ChargerDetailStatus chargerDetailStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee bookedByEmployee;

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
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
		return "ChargerDetails [detailId=" + detailId + ", chargerSlotDuration=" + chargerSlotDuration
				+ ", chargerDetailStatus=" + chargerDetailStatus + ", bookedByEmployee=" + bookedByEmployee + "]";
	}
	
	
	

}

