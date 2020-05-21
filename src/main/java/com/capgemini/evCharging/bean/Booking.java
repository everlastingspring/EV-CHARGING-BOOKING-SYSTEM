package com.capgemini.evCharging.bean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Booking {

	@Id
	private String ticketNo;
	@ManyToOne(cascade = CascadeType.ALL)
	private Charger bookedCharger;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee bookingByEmployee;
	private String bookedTiming; //"12:00"
	private Date bookedDate; //
	
	public Date getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public Charger getBookedCharger() {
		return bookedCharger;
	}
	public void setBookedCharger(Charger bookedCharger) {
		this.bookedCharger = bookedCharger;
	}
	public Employee getBookingByEmployee() {
		return bookingByEmployee;
	}
	public void setBookingByEmployee(Employee bookingByEmployee) {
		this.bookingByEmployee = bookingByEmployee;
	}
	public String getBookedTiming() {
		return bookedTiming;
	}
	public void setBookedTiming(String bookedTiming) {
		this.bookedTiming = bookedTiming;
	}
	@Override
	public String toString() {
		return "Booking [ticketNo=" + ticketNo + ", bookedCharger=" + bookedCharger + ", bookingByEmployee="
				+ bookingByEmployee + ", bookedTiming=" + bookedTiming + ", bookedDate=" + bookedDate + "]";
	}
	
	
	
	
	
}
