package com.capgemini.evCharging.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Station;

@Repository
public interface StationDao extends JpaRepository<Station, Integer>{

	@Query("select S from Station S where S.city=:city and S.campus=:campusLocation")
	public Optional<Station> checkIfStationExists(@Param("city") String city, @Param("campusLocation") String campusLocation);
	
	@Query("select S from Station S")
	public List<Station> getChargingStations();
}
