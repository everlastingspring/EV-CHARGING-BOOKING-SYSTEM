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
				+ startingDate + ", chargerActiveTimings="
				+ Arrays.toString(chargerActiveTimings) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(chargerActiveTimings);
		result = prime * result + ((chargerId == null) ? 0 : chargerId.hashCode());
		result = prime * result + ((chargerStation == null) ? 0 : chargerStation.hashCode());
		result = prime * result + ((chargerStatus == null) ? 0 : chargerStatus.hashCode());
		result = prime * result + ((chargerType == null) ? 0 : chargerType.hashCode());
		result = prime * result + ((slotDuration == null) ? 0 : slotDuration.hashCode());
		result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
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
		Charger other = (Charger) obj;
		if (!Arrays.equals(chargerActiveTimings, other.chargerActiveTimings))
			return false;
		if (chargerId == null) {
			if (other.chargerId != null)
				return false;
		} else if (!chargerId.equals(other.chargerId))
			return false;
		if (chargerStation == null) {
			if (other.chargerStation != null)
				return false;
		} else if (!chargerStation.equals(other.chargerStation))
			return false;
		if (chargerStatus != other.chargerStatus)
			return false;
		if (chargerType != other.chargerType)
			return false;
		if (slotDuration != other.slotDuration)
			return false;
		if (startingDate == null) {
			if (other.startingDate != null)
				return false;
		} else if (!startingDate.equals(other.startingDate))
			return false;
		return true;
	}
	
	
	

}
