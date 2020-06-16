package com.capgemini.evCharging.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.ChargerDetail;
import com.capgemini.evCharging.bean.ChargerDetailId;
import com.capgemini.evCharging.bean.enums.ChargerType;
import com.capgemini.evCharging.bean.enums.SlotDuration;

@Repository
public interface ChargerDetailDao extends JpaRepository<ChargerDetail, ChargerDetailId> {


	@Query("select D from ChargerDetail D")
	public List<ChargerDetail> getChargersDetaiListForType(@Param("forDate") Integer forDate,@Param("selectedChargerType") ChargerType selectedChargerType,@Param("stationId") String stationId);
	
	@Query("select D from ChargerDetail D")
	public List<ChargerDetail> getChargerDetailListForSlot(@Param("forDate") Integer forDate,@Param("slotDuration") SlotDuration slotDuration,@Param("stationId") String stationId);
	
	
	
}

//@Query("select D from ChargerDetail D where D.detailId.detailForDate=:forDate and D.detailId.charger.chargerStation.stationId=:stationId and D.detailId.charger.chargerType=:selectedChargerType")


//@Query("select D from ChargerDetail D where D.detailId.detailForDate=:forDate and D.detailId.charger.chargerStation.stationId=:stationId and D.detailId.charger.slotDuration=:slotDuration")
