package com.capgemini.evCharging.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.ChargerType;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")

	@GenericGenerator(

			name = "employee_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "E_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String mailId;
	
	private String empName;
	
	private String phoneNo;
	
	@Enumerated(EnumType.STRING)
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "Employee [mailId=" + mailId + ", empName=" + empName + ", phoneNo=" + phoneNo + ", employeeChargerType="
				+ employeeChargerType + ", employeeStation=" + employeeStation + "]";
	}
	
	
	

}
