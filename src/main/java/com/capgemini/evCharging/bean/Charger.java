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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import lombok.Data;

@Entity
@Data
public class Charger {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charger_seq")

	@GenericGenerator(

			name = "charger_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CH_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String chargerId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ChargerType chargerType;
	
	@Column(nullable = false)
	private LocalTime startTime;//9:00am
	
	@Column(nullable = false)
	private LocalTime endTime;//18:00pm
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ChargerStatus chargerStatus; 
	
	@Column(nullable = false)
	private LocalTime slotDuration; //1:30:00
	
	@Column(nullable = false)
	private LocalDate startingDate;//30/06/2020
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Station station;

}

