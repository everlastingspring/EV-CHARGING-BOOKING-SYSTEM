package com.capgemini.evCharging.bean;

public class ReportFormat {
	
	private Machine bookedMachine;
	private Long bookingsCount;
	
	public Machine getBookedMachine() {
		return bookedMachine;
	}
	public void setBookedMachine(Machine bookedMachine) {
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
