package com.capgemini.evCharging.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	private String mailId;
	
	private String phoneNo;
	@ManyToOne(cascade = CascadeType.ALL)
	private ChargerType employeeChargerType;
	@ManyToOne(cascade = CascadeType.ALL)
	private Station employeeStation;
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public ChargerType getEmployeeChargerType() {
		return employeeChargerType;
	}
	public void setEmployeeChargerType(ChargerType employeeChargerType) {
		this.employeeChargerType = employeeChargerType;
	}
	public Station getEmployeeStation() {
		return employeeStation;
	}
	public void setEmployeeStation(Station employeeStation) {
		this.employeeStation = employeeStation;
	}
	@Override
	public String toString() {
		return "Employee [mailId=" + mailId + ", phoneNo=" + phoneNo + ", employeeChargerType=" + employeeChargerType
				+ ", employeeStation=" + employeeStation + "]";
	}
	
	
	

}
