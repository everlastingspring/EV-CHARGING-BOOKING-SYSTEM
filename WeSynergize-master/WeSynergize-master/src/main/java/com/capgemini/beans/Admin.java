package com.capgemini.beans;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private String user_name;
	private String mail_id;
	private String phone_number;
	private String password;
	private byte[] salt;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Admin [user_name=" + user_name + ", mail_id=" + mail_id + ", phone_number=" + phone_number
				+ ", password=" + password + ", salt=" + Arrays.toString(salt) + "]";
	}
	
	
}
