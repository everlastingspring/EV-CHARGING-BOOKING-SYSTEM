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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.ChargerType;

import lombok.Data;

@Entity
@Data
public class Employee 
	implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10688140745626690L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")

	@GenericGenerator(

			name = "employee_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "E_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String employeeId;
	
	@Column(nullable = false,unique = true)
	private String mailId;
	
	@Column(nullable = false)
	private String empName;
	
	@Column(nullable = false,unique = true,length = 10)
	private String phoneNo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)//level_1 
	private ChargerType employeeChargerType;
	
	@Column(nullable = false)
	private String campus;
	
	@Column(nullable = false)
	private String city;
	
	@Transient private String password;
}