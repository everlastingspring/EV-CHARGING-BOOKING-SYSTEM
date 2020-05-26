package com.capgemini.evCharging.bean;

import java.sql.Date;
import java.util.Arrays;

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

import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;



@Entity
public class Charger {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charger_seq")

	@GenericGenerator(

			name = "charger_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CH_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String chargerId;
	
	@Enumerated(EnumType.STRING)
	private ChargerType chargerType;
	
	@Enumerated(EnumType.STRING)
	private ChargerStatus chargerStatus; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Station chargerStation;
	
	@Enumerated(EnumType.STRING)
	private SlotDuration slotDuration; 
	
	private Date startingDate;
	
	private Date removalDate; 
	
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

	public ChargerStatus getChargerStatus() {
		return chargerStatus;
	}

	public void setChargerStatus(ChargerStatus chargerStatus) {
		this.chargerStatus = chargerStatus;
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

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getRemovalDate() {
		return removalDate;
	}

	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}

	public String[] getChargerActiveTimings() {
		return chargerActiveTimings;
	}

	public void setChargerActiveTimings(String[] chargerActiveTimings) {
		this.chargerActiveTimings = chargerActiveTimings;
	}

	@Override
	public String toString() {
		return "Charger [chargerId=" + chargerId + ", chargerType=" + chargerType + ", chargerStatus=" + chargerStatus
				+ ", chargerStation=" + chargerStation + ", slotDuration=" + slotDuration + ", startingDate="
				+ startingDate + ", removalDate=" + removalDate + ", chargerActiveTimings="
				+ Arrays.toString(chargerActiveTimings) + "]";
	}

}
