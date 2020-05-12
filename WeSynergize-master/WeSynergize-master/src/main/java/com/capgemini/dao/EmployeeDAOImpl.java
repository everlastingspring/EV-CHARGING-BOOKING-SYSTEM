package com.capgemini.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;
import com.capgemini.beans.Employee_data;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("electriccar_charging_management");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public Boolean login(Employee_data employee) {

		try {
			Employee_data employee_data = entityManager.find(Employee_data.class, employee.getUser_name());
			if (employee_data.getUser_name().equals(employee.getUser_name())) {
				if (employee_data.getPassword()
						.equals(hashedPassword(employee.getPassword(), employee_data.getSalt()))) {
					return true;
				} 
				else 
				{
					return false;
				}
			} 
			else 
			{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return false;
	}

	@Override
	public Boolean register(Employee_data employee) {
		try {

			if (employee.getMail_id().matches("^(.+)@(capgemini.com)$")) {
				if (employee.getPassword().matches("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}")) {
					Employee_data employee_data = new Employee_data();
					employee_data.setMail_id(employee.getMail_id());
					byte[] salt = createSalt();
					employee_data.setSalt(salt);
					employee_data.setPassword(hashedPassword(employee.getPassword(), salt));
					employee_data.setUser_name(employee.getUser_name());
					employee_data.setPhone_number(employee.getPhone_number());
					employee_data.setCharger_level(employee.getCharger_level());
					employee_data.setLocation(employee.getLocation());
					entityManager.getTransaction().begin();
					entityManager.persist(employee_data);
					entityManager.getTransaction().commit();
					entityManager.close();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return false;
	}

	private String hashedPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
		String algorithm = "MD5";
		return generateHash(password, algorithm, salt);
	}

	private static byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}

	private static String generateHash(String password, String algorithm, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		digest.update(salt);
		byte[] hash = digest.digest(password.getBytes());
		return bytesToHexString(hash);
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private static String bytesToHexString(byte[] hash) {
		char[] hexChars = new char[hash.length * 2];
		for (int i = 0; i < hash.length; i++) {
			int k = hash[i] & 0xFF;
			hexChars[i * 2] = hexArray[k >>> 4];
			hexChars[i * 2 + 1] = hexArray[k & 0x0f];
		}
		return new String(hexChars);
	}

}
