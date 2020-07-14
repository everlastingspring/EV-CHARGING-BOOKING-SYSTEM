/*
 * Copyright (c) 2020 Capgemini and/or its affiliates. All rights reserved.
 * CAPGEMINI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */
package com.capgemini.evCharging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,String> {
	
	@Query("select e from Employee e where e.mailId=:mailId")
	public Employee getEmployeeFromMailid(@Param("mailId") String mailId);

}
