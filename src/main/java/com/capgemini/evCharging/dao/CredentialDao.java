package com.capgemini.evCharging.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.evCharging.bean.Credential;

@Repository
public interface CredentialDao extends JpaRepository<Credential, String>{

}
