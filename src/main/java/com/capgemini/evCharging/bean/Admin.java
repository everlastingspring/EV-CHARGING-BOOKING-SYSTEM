package com.capgemini.evCharging.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")

	@GenericGenerator(

			name = "admin_seq",

			strategy = "com.capgemini.evCharging.bean.StringPrefixedSequenceIdGenerator",

			parameters = {

					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),

					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
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
