package com.capgemini.evCharging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Station;

@Repository
public interface StationDao extends JpaRepository<Station, Integer>{

	
}
