package com.proxy.proxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proxy.proxy.models.EntityServer;

public interface EntityServerRepository extends JpaRepository<EntityServer, String> {
	
	 @Query("SELECT es FROM EntityServer es WHERE es.EntityName = :entityName and es.ServiceName = :serviceName")
	 public EntityServer findByEntityName(@Param("entityName") String EntityName, @Param("serviceName") String serviceName);
}
