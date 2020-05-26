package com.capgemini.evCharging.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
public class Station {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_seq")

	@GenericGenerator(

			name = "station_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ST_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
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
