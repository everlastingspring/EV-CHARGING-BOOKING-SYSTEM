package com.capgemini.evCharging.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Charger;
import com.capgemini.evCharging.bean.enums.ChargerType;
@Repository
public interface ChargerDao extends JpaRepository<Charger, String>{
	
	@Query("select C from Charger C where C.chargerType=:level")
	public List<Charger> getChargersByLevel(@Param("level") String level);
	

	@Query("select C from Charger C where C.chargerType=:selectedChargerType and C.station.campus=:campus and C.station.city=:city")
	public List<Charger> getChargersOfStationAndType(@Param("selectedChargerType") ChargerType selectedChargerType,@Param("campus") String campus,@Param("city") String city);
	
	@Query("select C from Charger C where C.station.campus=:campus and C.station.city=:city")
	public List<Charger> getChargersOfStation(@Param("campus") String campus,@Param("city") String city);
	
}
