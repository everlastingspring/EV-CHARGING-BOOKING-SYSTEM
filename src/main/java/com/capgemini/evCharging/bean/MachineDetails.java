package com.capgemini.evCharging.bean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.evCharging.bean.enums.MachineDetailStatus;
import com.capgemini.evCharging.bean.enums.SlotDuration;


//Non Entity class
public class MachineDetails {
	private HashMap<MachineDetailKey, ArrayList<MachineDetailValue>> machineDetails = new HashMap<MachineDetailKey, ArrayList<MachineDetailValue>>();

	public HashMap<MachineDetailKey, ArrayList<MachineDetailValue>> getMachineDetails() {
		return machineDetails;
	}

	public void setMachineDetails(HashMap<MachineDetailKey, ArrayList<MachineDetailValue>> machineDetails) {
		this.machineDetails = machineDetails;
	}
	
	public MachineDetails() {
		
		// 60 minutes slot duration
		LocalTime startTime = LocalTime.of(0, 0);
		LocalTime endTime = LocalTime.of(1, 0);

		for(int mins = 60;mins <= 24*60; mins+=60) {
		
			machineDetails.put(new MachineDetailKey(startTime, endTime,SlotDuration.SIXTY), new ArrayList<MachineDetailValue>());
			
			startTime = startTime.plusMinutes(60);
			endTime = endTime.plusMinutes(60);
			
			
			
		}

		

//		//30 minutes slot duration 
		startTime = LocalTime.of(0, 0);
		endTime = LocalTime.of(0, 30);

		for(int mins = 30;mins <= 24*60; mins+= 30) {
			
			machineDetails.put(new MachineDetailKey(startTime, endTime,SlotDuration.THIRTY), new ArrayList<MachineDetailValue>());
			

			startTime = startTime.plusMinutes(30);
			endTime = endTime.plusMinutes(30);
			
			
		}


//		//15 minutes slot duration 
		startTime = LocalTime.of(0, 0);
		endTime = LocalTime.of(0, 15);
		for(int mins = 15;mins <= 24*60; mins+= 15) {
			
			
			machineDetails.put(new MachineDetailKey(startTime, endTime,SlotDuration.FIFTEEN), new ArrayList<MachineDetailValue>());
			startTime = startTime.plusMinutes(15);
			endTime = endTime.plusMinutes(15);
			
		}
		
		System.out.println(machineDetails.keySet().size());
		
	}

	@Override
	public String toString() {
		return "MachineDetails [machineDetails=" + machineDetails + "]";
	}
	
	
	
}


