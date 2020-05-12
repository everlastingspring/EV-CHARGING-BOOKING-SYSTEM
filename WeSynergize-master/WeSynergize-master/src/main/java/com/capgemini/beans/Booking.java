package com.capgemini.beans;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Booking implements Serializable {
	
	private static final long serialVersionUID = 4L;
	@Id
	private Integer booking_id; 
	private String booked_charger;
	@Enumerated(EnumType.STRING)
	private ChargerLevel charger_level;
	private LocalTime charger_timings;
	private Duration charging_duration;
	private LocalDate booked_date;
	@Enumerated(EnumType.STRING)
	private Location booked_location;
	@ManyToOne(cascade=CascadeType.ALL)
	private Employee_data employee;
	
	public Employee_data getEmployee() {
		return employee;
	}
	public void setEmployee(Employee_data employee) {
		this.employee = employee;
	}
	public Integer getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}
	public String getBooked_charger() {
		return booked_charger;
	}
	public void setBooked_charger(String booked_charger) {
		this.booked_charger = booked_charger;
	}
	public ChargerLevel getCharger_level() {
		return charger_level;
	}
	public void setCharger_level(ChargerLevel charger_level) {
		this.charger_level = charger_level;
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
	public LocalDate getBooked_date() {
		return booked_date;
	}
	public void setBooked_date(LocalDate booked_date) {
		this.booked_date = booked_date;
	}
	public Location getBooked_location() {
		return booked_location;
	}
	public void setBooked_location(Location booked_location) {
		this.booked_location = booked_location;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Booking [booking_id=" + booking_id + ", booked_charger=" + booked_charger + ", charger_level="
				+ charger_level + ", charger_timings=" + charger_timings + ", charging_duration=" + charging_duration
				+ ", booked_date=" + booked_date + ", booked_location=" + booked_location + ", employee=" + employee
				+ "]";
	}
	
	
	
	

}
