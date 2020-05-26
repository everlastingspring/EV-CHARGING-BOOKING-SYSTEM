package com.capgemini.evCharging.bean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")

	@GenericGenerator(

			name = "booking_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "BO_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String ticketNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Charger bookedCharger;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee bookingByEmployee;
	
	private String bookedTiming; //"12:00"
	
	private Date bookedDate; 
	
	private String bookedForMins;
	
	
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
	public String getBookedForMins() {
		return bookedForMins;
	}
	public void setBookedForMins(String bookedForMins) {
		this.bookedForMins = bookedForMins;
	}
	@Override
	public String toString() {
		return "Booking [ticketNo=" + ticketNo + ", bookedCharger=" + bookedCharger + ", bookingByEmployee="
				+ bookingByEmployee + ", bookedTiming=" + bookedTiming + ", bookedDate=" + bookedDate
				+ ", bookedForMins=" + bookedForMins + "]";
	}

	
}
