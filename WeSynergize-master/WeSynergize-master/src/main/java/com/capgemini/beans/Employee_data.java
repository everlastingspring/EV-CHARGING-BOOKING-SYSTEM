package com.capgemini.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Employee_data implements Serializable {
	
	private static final long serialVersionUID = 3L;
	
	@Id
	private String user_name;
	@Column(name = "mail_id",unique=true,updatable=false)
	private String mail_id;
	@Column(unique=true)
	private String phone_number;
	@Enumerated(EnumType.STRING)
	private ChargerLevel charger_level;
	@Enumerated(EnumType.STRING)
	private Location location;
	private String password;
	private byte[] salt;
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
	private Collection<Booking> bookings=new ArrayList<Booking>();
	
	public Collection<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}
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
	public ChargerLevel getCharger_level() {
		return charger_level;
	}
	public void setCharger_level(ChargerLevel charger_level) {
		this.charger_level = charger_level;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
		return "Employee_data [user_name=" + user_name + ", mail_id=" + mail_id + ", phone_number=" + phone_number
				+ ", charger_level=" + charger_level + ", location=" + location + ", password=" + password + ", salt="
				+ Arrays.toString(salt) + ", bookings=" + bookings + "]";
	}
	

	
	
}
