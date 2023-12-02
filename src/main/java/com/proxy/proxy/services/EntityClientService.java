package com.proxy.proxy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.proxy.proxy.models.EntityClient;
import com.proxy.proxy.repositories.EntityClientRepository;

@Service
public class EntityClientService {
	
	@Autowired
	private EntityClientRepository clientRepo;
	
	public boolean save(EntityClient ec){
		EntityClient entityClient = clientRepo.save(ec);
		if(null == entityClient){
			return false;
		}
		return true;
	}
	
	public EntityClient findById(String id) {
		EntityClient entityClient = clientRepo.findByID(id);
		return entityClient;
	}
	@Cacheable(value = "EntityClient", key = "#sender + #serviceName")
	public EntityClient findByEntityName(String sender, String serviceName) {
		EntityClient entityClient = clientRepo.findByEntityName(sender, serviceName);
		return entityClient;
	}

}
