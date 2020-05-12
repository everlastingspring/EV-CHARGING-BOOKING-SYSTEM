package com.capgemini.beans;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Charger implements Serializable {
	
	private static final long serialVersionUID = 2L;
	@Id
	private String charger_id;
	@Enumerated(EnumType.STRING)
	private ChargerLevel charger_level;
	@Enumerated(EnumType.STRING)
	private ChargerStatus charger_status;
	private LocalTime charger_timings;
	private Duration charging_duration;
	private LocalDate working_start_date;
	@Enumerated(EnumType.STRING)
	private Location location;
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
	private Collection<Booking> bookings=new ArrayList<Booking>();
	
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
	public String getCharger_id() {
		return charger_id;
	}
	public void setCharger_id(String charger_id) {
		this.charger_id = charger_id;
	}
	public ChargerLevel getCharger_level() {
		return charger_level;
	}
	public void setCharger_level(ChargerLevel charger_level) {
		this.charger_level = charger_level;
	}
	public ChargerStatus getCharger_status() {
		return charger_status;
	}
	public void setCharger_status(ChargerStatus charger_status) {
		this.charger_status = charger_status;
	}
	public LocalTime getCharger_timings() {
		return charger_timings;
	}
	public void setCharger_timings(LocalTime charger_timings) {
		this.charger_timings = charger_timings;
	}
	public Duration getCharging_duration() {
		return charging_duration;
	}
	public void setCharging_duration(Duration charging_duration) {
		this.charging_duration = charging_duration;
	}
	public LocalDate getWorking_start_date() {
		return working_start_date;
	}
	public void setWorking_start_date(LocalDate working_start_date) {
		this.working_start_date = working_start_date;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Charger [charger_id=" + charger_id + ", charger_level=" + charger_level + ", charger_status="
				+ charger_status + ", charger_timings=" + charger_timings + ", charging_duration=" + charging_duration
				+ ", working_start_date=" + working_start_date + ", location=" + location + ", bookings=" + bookings
				+ "]";
	}
	
	
	
	
}
