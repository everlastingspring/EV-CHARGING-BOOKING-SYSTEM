package com.capgemini.evCharging.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Credential;

@Repository
public interface CredentialDao extends JpaRepository<Credential, String>{

	@Query("select c from Credential c where c.mailId=:mailId")
	Optional<Credential> getAccountFromMailId(@Param("mailId")String mailId);

}
