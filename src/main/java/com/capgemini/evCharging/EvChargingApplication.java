package com.capgemini.evCharging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.evCharging.bean.Employee;
import com.capgemini.evCharging.exception.EvChargingException;
import com.capgemini.evCharging.service.EvChargingService;
import com.capgemini.evCharging.service.EvChargingServiceImpl;

@SpringBootApplication
public class EvChargingApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EvChargingApplication.class, args);
		
//		EvChargingService chargingService = new EvChargingServiceImpl();
//		Employee emp= new Employee();
//		chargingService.registerEmployee(emp);
	}

}
