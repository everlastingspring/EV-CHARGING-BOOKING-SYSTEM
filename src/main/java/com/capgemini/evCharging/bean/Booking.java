package com.capgemini.evCharging.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.BookingStatus;

import lombok.Data;
@DynamicInsert
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
	private String bookingId;
	
	@ManyToOne
	private Charger bookedCharger;
	
	@ManyToOne
	private Employee bookingByEmployee;
	
	@Column(nullable = false)
	private LocalDate bookedDate;

	@Column(nullable = false)
	private LocalTime startTime;//9:00am
	
	@Column(nullable = false)
	private LocalTime endTime;//10:30am
	
	@ColumnDefault(value = "'BOOKED'")
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus; 
	
}