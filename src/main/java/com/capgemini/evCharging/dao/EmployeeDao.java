package com.capgemini.evCharging.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {

	@Query("select E from Employee E where E.mailId=:mailId")
	public Optional<Employee> findByMailId(@Param("mailId") String mailId);
}
