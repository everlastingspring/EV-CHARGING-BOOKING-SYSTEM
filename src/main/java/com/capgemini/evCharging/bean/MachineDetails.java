package com.capgemini.evCharging.bean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.capgemini.evCharging.bean.enums.MachineDetailStatus;


//Non Entity class
public class MachineDetails {
	private HashMap<MachineDetailKey, List<MachineDetailValue>> machineDetails;

	public HashMap<MachineDetailKey, List<MachineDetailValue>> getMachineDetails() {
		return machineDetails;
	}

	public void setMachineDetails(HashMap<MachineDetailKey, List<MachineDetailValue>> machineDetails) {
		this.machineDetails = machineDetails;
	}
	
	public MachineDetails() {
		
		// 60 minutes slot duration
		LocalTime startTime = LocalTime.of(0, 0);
		LocalTime endTime = LocalTime.of(1, 0);

		for(int mins = 0;mins < 24*60; mins+= 60) {
			startTime = endTime;
			endTime.plusMinutes(mins);
			machineDetails.put(new MachineDetailKey(startTime, endTime), new ArrayList<MachineDetailValue>());
		}

		//30 minutes slot duration 
		startTime = LocalTime.of(0, 0);
		endTime = LocalTime.of(0, 30);
		for(int mins = 0;mins < 24*60; mins+= 30) {
			startTime = endTime;
			endTime.plusMinutes(mins);
			machineDetails.put(new MachineDetailKey(startTime, endTime), new ArrayList<MachineDetailValue>());
		}


		//15 minutes slot duration 
		startTime = LocalTime.of(0, 0);
		endTime = LocalTime.of(0, 15);
		for(int mins = 0;mins < 24*60; mins+= 15) {
			startTime = endTime;
			endTime.plusMinutes(mins);
			machineDetails.put(new MachineDetailKey(startTime, endTime), new ArrayList<MachineDetailValue>());
		}
		
	}
	
}


