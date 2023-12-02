package com.proxy.proxy.services;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.proxy.proxy.models.EntityACL;
import com.proxy.proxy.repositories.EntityACLRepository;

@Service
public class EntityACLService {
	@Autowired
	private EntityACLRepository aclRepo;
	
	@Cacheable( key = "#sender + #receiver" , value = "EntityACL")
	public LinkedHashMap<String, Object> findByPrivilegeAndIp(String serviceName, String sender, String receiver, String ip){
		LinkedHashMap<String, Object> acl = aclRepo.findByPrivilegeAndIp(serviceName, sender, receiver, ip);
		return acl;
	}
}
