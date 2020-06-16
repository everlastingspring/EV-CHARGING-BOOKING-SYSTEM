package com.capgemini.evCharging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Admin;


@Repository
public interface AdminDao extends JpaRepository<Admin, String> {

}


