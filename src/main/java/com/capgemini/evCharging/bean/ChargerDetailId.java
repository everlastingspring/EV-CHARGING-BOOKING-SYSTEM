package com.capgemini.evCharging.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



public class ChargerDetailId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "chargerId")
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((charger == null) ? 0 : charger.hashCode());
		result = prime * result + ((detailForDate == null) ? 0 : detailForDate.hashCode());
		result = prime * result + ((detailFortime == null) ? 0 : detailFortime.hashCode());
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
		ChargerDetailId other = (ChargerDetailId) obj;
		if (charger == null) {
			if (other.charger != null)
				return false;
		} else if (!charger.equals(other.charger))
			return false;
		if (detailForDate == null) {
			if (other.detailForDate != null)
				return false;
		} else if (!detailForDate.equals(other.detailForDate))
			return false;
		if (detailFortime == null) {
			if (other.detailFortime != null)
				return false;
		} else if (!detailFortime.equals(other.detailFortime))
			return false;
		return true;
	}
	
	
	
	

}
