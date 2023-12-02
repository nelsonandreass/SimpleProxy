package com.proxy.proxy.repositories;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proxy.proxy.models.EntityClient;

public interface EntityClientRepository extends JpaRepository<EntityClient, String> {
	 @Query("SELECT ec FROM EntityClient ec WHERE ec.EntityName = :entityName and ec.ServiceName = :serviceName")
	 public EntityClient findByEntityName(@Param("entityName") String EntityName, @Param("serviceName") String serviceName);
	 
	 public EntityClient findByID(String id);
	 
}
