package com.capgemini.evCharging.bean;

public enum SlotDuration {
	
	FIFTEEN(15), THIRTY(30), SIXTY(60) ;
	
	private Integer value;
	
	
	SlotDuration(Integer value) {
		this.value = value;
	}
	
	

}
