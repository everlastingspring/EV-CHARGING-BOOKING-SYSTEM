package com.capgemini.evCharging.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.capgemini.evCharging.bean.enums.ChargerType;

import lombok.Data;

@Entity
@Data
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
	@JoinTable(name = "Credential")
	@JoinColumns(value = { @JoinColumn (name = "employeeId" ,referencedColumnName = "employeeId")})
	private String employeeId;
	
	@JoinTable(name = "Credential")
	@JoinColumns(value = { @JoinColumn (name = "mailId" ,unique = true,referencedColumnName = "mailId")})
	private String mailId;
	
	@Column(nullable = false)
	private String empName;
	
	@Column(nullable = false,unique = true,length = 10)
	private String phoneNo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ChargerType employeeChargerType;
	
	@OneToMany(mappedBy = "bookingByEmployee")
	private List<Booking> bookings;
	
	@Column(nullable = false)
	private String campus;
	
	@Column(nullable = false)
	private String city;
	
	@Transient private String password;
}