package com.capgemini.evCharging.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.ChargerStatus;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;

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
	
	@Enumerated(EnumType.STRING)
	private ChargerType chargerType;
	
	private String[] chargerActiveTimings;
	
	@Enumerated(EnumType.STRING)
	private ChargerStatus chargerStatus; 
	
	@Enumerated(EnumType.STRING)
	private SlotDuration slotDuration; 
	
	private Date startingDate;
	
	private String campus;
	
	private String city;

}

