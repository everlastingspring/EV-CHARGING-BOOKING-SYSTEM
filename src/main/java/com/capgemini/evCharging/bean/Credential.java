package com.capgemini.evCharging.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Data
public class Credential {
	
	@Id
	private String mailId;
	@ColumnDefault(value = "false")
	private Boolean isAdmin;
	
	private String password; 
	
	private byte[] salt;
	
}
