package com.capgemini.evCharging.bean;

import java.util.List;
import java.util.Map;

public class ChargerInfo {
	
	private SlotDuration duration; //60
	 
	private List<Map<String, List<Charger>>> bookedChargerBookings;
	private List<Map<String, List<Charger>>> availableChargers;
	public SlotDuration getDuration() {
		return duration;
	}
	public void setDuration(SlotDuration duration) {
		this.duration = duration;
	}
	public List<Map<String, List<Charger>>> getBookedChargerBookings() {
		return bookedChargerBookings;
	}
	public void setBookedChargerBookings(List<Map<String, List<Charger>>> bookedChargerBookings) {
		this.bookedChargerBookings = bookedChargerBookings;
	}
	public List<Map<String, List<Charger>>> getAvailableChargers() {
		return availableChargers;
	}
	public void setAvailableChargers(List<Map<String, List<Charger>>> availableChargers) {
		this.availableChargers = availableChargers;
	}
	@Override
	public String toString() {
		return "ChargerInfo [duration=" + duration + ", bookedChargerBookings=" + bookedChargerBookings
				+ ", availableChargers=" + availableChargers + "]";
	}
	
	
	

}


//15 mins
//bookin... : [ {"12:30" : [{charger1 }, {charger2 }, ]}, {"12:45": {},{}  ]