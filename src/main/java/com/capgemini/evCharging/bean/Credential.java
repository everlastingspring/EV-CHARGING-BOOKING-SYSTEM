package com.capgemini.evCharging.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Data;
@DynamicInsert
@Entity
@Data
public class Credential {
	
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