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
import javax.persistence.Id;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;
@DynamicInsert
@Entity
@Data
public class Credential 
	implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1168361353988883836L;

	@Id
	private String employeeId;
	
	private String mailId;
	
	@ColumnDefault(value = "false")
	private Boolean isAdmin = false;
	
	@Column(nullable = false)
	private String password; 
	
	@Column(nullable = false)
	private byte[] salt;
	
}