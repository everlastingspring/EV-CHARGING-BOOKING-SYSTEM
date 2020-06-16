package com.capgemini.evCharging.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.BookingStatus;
import com.capgemini.evCharging.bean.enums.SlotDuration;

import lombok.Data;

@Entity
@Data
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
	
	@Enumerated(EnumType.STRING)
	private SlotDuration slotDuration;
	
	private Date bookedDate;
	
	private String bookedTiming; 
	
	@Enumerated(EnumType.STRING)
	private BookingStatus BookingStatus; 
	
}
