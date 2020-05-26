package com.capgemini.evCharging.bean;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credential {
	
	@Id
	private String mailId;
	
	private String hashedPassword; 
	
	private byte[] saltArray; //hashed with array
	
	private Boolean isAdmin;

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public byte[] getSaltArray() {
		return saltArray;
	}

	public void setSaltArray(byte[] saltArray) {
		this.saltArray = saltArray;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Credential [mailId=" + mailId + ", hashedPassword=" + hashedPassword + ", saltArray="
				+ Arrays.toString(saltArray) + ", isAdmin=" + isAdmin + "]";
	}


}
