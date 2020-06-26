package com.capgemini.evCharging.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Credential;

@Repository
public interface CredentialDao extends JpaRepository<Credential, Integer>{
	
	@Query("select C from Credential C where C.mailId=:mailId")
	public Optional<Credential> findByMailId(@Param("mailId") String mailId);

}
