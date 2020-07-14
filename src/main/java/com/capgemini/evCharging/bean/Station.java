/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Data
public class Station 
	implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5881933450262723036L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_seq")

	@GenericGenerator(

			name = "station_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ST_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String stationId;
	
	@Column(nullable = false)
	private String campus;
	
	@Column(nullable = false)
	private String city;
}
