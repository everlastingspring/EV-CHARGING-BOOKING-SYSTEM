package com.capgemini.evCharging.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Station {
	
	@Id
	private String stationId;
	private String city;
	private String campusLocation;
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCampusLocation() {
		return campusLocation;
	}
	public void setCampusLocation(String campusLocation) {
		this.campusLocation = campusLocation;
	}
	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", city=" + city + ", campusLocation=" + campusLocation + "]";
	}
	

}
