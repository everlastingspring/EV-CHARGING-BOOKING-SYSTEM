package com.capgemini.evCharging.bean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.capgemini.evCharging.bean.enums.ChargerDetailStatus;
import com.capgemini.evCharging.bean.enums.SlotDuration;

@Entity
public class ChargerDetail {
	
	@Embedded
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
		return "ChargerDetails [detailId=" + detailId + ", chargerSlotDuration=" + chargerSlotDuration
				+ ", chargerDetailStatus=" + chargerDetailStatus + ", bookedByEmployee=" + bookedByEmployee + "]";
	}
	
	
	

}


@Embeddable
class ChargerDetailId {
	
	private Charger charger;
	private Date detailForDate;
	private String detailFortime;
	
	public Charger getCharger() {
		return charger;
	}
	public void setCharger(Charger charger) {
		this.charger = charger;
	}
	public Date getDetailForDate() {
		return detailForDate;
	}
	public void setDetailForDate(Date detailForDate) {
		this.detailForDate = detailForDate;
	}
	public String getDetailFortime() {
		return detailFortime;
	}
	public void setDetailFortime(String detailFortime) {
		this.detailFortime = detailFortime;
	}
	@Override
	public String toString() {
		return "ChargerDetailId [charger=" + charger + ", detailForDate=" + detailForDate + ", detailFortime="
				+ detailFortime + "]";
	}
	
    
}