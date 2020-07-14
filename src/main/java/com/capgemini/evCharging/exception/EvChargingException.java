/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.exception;

public class EvChargingException extends Exception {


	private static final long serialVersionUID = 1L;

	public EvChargingException() {
		super();
	}

	public EvChargingException(String message) {
		super(message);
	}
}