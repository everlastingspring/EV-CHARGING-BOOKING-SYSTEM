package com.capgemini.evCharging.bean;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Charger {
	@Id
	private String chargerId;
	@Enumerated(EnumType.STRING)
	private ChargerType chargerType; 
	private Boolean isActive; //
	@ManyToOne(cascade = CascadeType.ALL)
	private Station chargerStation;
	@Enumerated(EnumType.STRING)
	private SlotDuration slotDuration; 
	private Date startingDate;
	private Date stoppingDate; //starting_Date <= stopping Date 32/13/3000
	private String[] chargerActiveTimings; //["11:00-13:00","16:00-18:00"]
	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}
	public ChargerType getChargerType() {
		return chargerType;
	}
	public void setChargerType(ChargerType chargerType) {
		this.chargerType = chargerType;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Station getChargerStation() {
		return chargerStation;
	}
	public void setChargerStation(Station chargerStation) {
		this.chargerStation = chargerStation;
	}
	public SlotDuration getSlotDuration() {
		return slotDuration;
	}
	public void setSlotDuration(SlotDuration slotDuration) {
		this.slotDuration = slotDuration;
	}
	public Date getstartingDate() {
		return startingDate;
	}
	public void setstartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getStoppingDate() {
		return stoppingDate;
	}
	public void setStoppingDate(Date stoppingDate) {
		this.stoppingDate = stoppingDate;
	}
	public String[] getChargerActiveTimings() {
		return chargerActiveTimings;
	}
	public void setChargerActiveTimings(String[] chargerActiveTimings) {
		this.chargerActiveTimings = chargerActiveTimings;
	}
	@Override
	public String toString() {
		return "Charger [chargerId=" + chargerId + ", chargerType=" + chargerType + ", isActive=" + isActive
				+ ", chargerStation=" + chargerStation + ", slotDuration=" + slotDuration + ", startingDate="
				+ startingDate + ", stoppingDate=" + stoppingDate + ", chargerActiveTimings="
				+ Arrays.toString(chargerActiveTimings) + "]";
	}
	
	
	
	
	
	

}
