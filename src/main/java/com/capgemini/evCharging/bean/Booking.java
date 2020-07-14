/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.bean;

import java.io.Serializable;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.BookingStatus;

import lombok.Data;
@Entity
@Data
public class Booking 
    implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6175890716709534532L;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Charger bookedCharger;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee bookingByEmployee;
	
	@Column(nullable = false)
	private LocalDate bookedDate;

	@Column(nullable = false)
	private LocalTime startTime;
	
	@Column(nullable = false)
	private LocalTime endTime;
	
	@Column(nullable = false)
	private LocalTime slotDuration;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus; 
	
}