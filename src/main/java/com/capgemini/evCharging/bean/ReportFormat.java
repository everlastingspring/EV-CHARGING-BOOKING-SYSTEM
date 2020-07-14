/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.bean;

import lombok.Data;

@Data
public class ReportFormat {
	
	private Charger bookedMachine;
	private Long bookingsCount;

}
