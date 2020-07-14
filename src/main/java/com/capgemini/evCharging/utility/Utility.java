/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.evCharging.bean.Booking;
import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.enums.BookingStatus;

public class Utility {
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static String hashedPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
		String algorithm = "MD5";
		return generateHash(password, algorithm, salt);
	}
	
	private static String generateHash(String password, String algorithm, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		digest.update(salt);
		byte[] hash = digest.digest(password.getBytes());
		return bytesToHexString(hash);
	}
	
	public static byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}
	
	private static String bytesToHexString(byte[] hash) {
		char[] hexChars = new char[hash.length * 2];
		for (int i = 0; i < hash.length; i++) {
			int k = hash[i] & 0xFF;
			hexChars[i * 2] = hexArray[k >>> 4];
			hexChars[i * 2 + 1] = hexArray[k & 0x0f];
		}
		return new String(hexChars);
	}

	public static List<Booking> createBookings(Charger charger) {
		List<Booking> bookings = new ArrayList<Booking>();
		for (LocalDate startDate = charger.getStartingDate(); startDate
				.isBefore(charger.getStartingDate().plusDays(7)); startDate = startDate.plusDays(1)) {
			DayOfWeek dayOfWeek=startDate.getDayOfWeek();
			if(dayOfWeek==DayOfWeek.SATURDAY || dayOfWeek==DayOfWeek.SUNDAY) {
				break;
			}
				for (LocalTime startTime = charger.getStartTime(); startTime
						.isBefore(charger.getEndTime()); startTime = startTime
								.plusMinutes(charger.getSlotDuration().get(ChronoField.MINUTE_OF_DAY))) {
					Booking booking = new Booking();
					booking.setBookedCharger(charger);
					booking.setBookedDate(startDate);
					booking.setBookingStatus(BookingStatus.AVAILABLE);
					booking.setSlotDuration(charger.getSlotDuration());
					booking.setStartTime(startTime);
					booking.setEndTime(startTime.plusMinutes(charger.getSlotDuration().get(ChronoField.MINUTE_OF_DAY)));
					bookings.add(booking);
				}
		}
		return bookings;
	}
}