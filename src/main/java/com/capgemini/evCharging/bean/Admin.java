package com.capgemini.evCharging.bean;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Admin {

	@Id
	private String mailId;
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	
	@Override
	public String toString() {
		return "Admin [mailId=" + "mailId ] ";
	}
	
	
}
