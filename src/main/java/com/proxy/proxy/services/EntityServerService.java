package com.proxy.proxy.services;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.proxy.proxy.models.EntityServer;
import com.proxy.proxy.repositories.EntityServerRepository;

@Service
public class EntityServerService {
	
	@Autowired
	private EntityServerRepository serverRepo;
	
	public boolean save(EntityServer es) {
		EntityServer entityServer = serverRepo.save(es);
		if(null == entityServer) {
			return false;
		}
		return true;
	}
	
	@Cacheable(value = "EntityServer", key = "#receiver + #serviceName")
	public EntityServer findByEntityName(String receiver, String serviceName) {
		EntityServer entityServer = serverRepo.findByEntityName(receiver, serviceName);
		return entityServer;
	}
}
