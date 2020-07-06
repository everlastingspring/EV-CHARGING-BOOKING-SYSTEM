package com.capgemini.evCharging.bean;

public class ReportFormat {
	
	private Charger bookedMachine;
	private Long bookingsCount;
	
	public Charger getBookedMachine() {
		return bookedMachine;
	}
	public void setBookedMachine(Charger bookedMachine) {
		this.bookedMachine = bookedMachine;
	}
	public Long getBookingsCount() {
		return bookingsCount;
	}
	public void setBookingsCount(Long bookingsCount) {
		this.bookingsCount = bookingsCount;
	}
	@Override
	public String toString() {
		return "ReportFormat [bookedMachine=" + bookedMachine + ", bookingsCount=" + bookingsCount + "]";
	}
	
	

}
