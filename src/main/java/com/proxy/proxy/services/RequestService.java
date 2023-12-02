package com.proxy.proxy.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxy.proxy.models.EntityACL;
import com.proxy.proxy.models.EntityClient;
import com.proxy.proxy.models.EntityServer;
import com.proxy.proxy.repositories.EntityACLRepository;
import com.proxy.proxy.repositories.EntityClientRepository;
import com.proxy.proxy.repositories.EntityServerRepository;

@Service
public class RequestService {
	@Autowired
	private EntityClientService clientService;
	@Autowired
	private EntityACLService aclService;
	@Autowired
	private EntityServerService serverService;
	
	public EntityClient checkClient(Map<String, String> request) {
		EntityClient checkClient = clientService.findByEntityName(request.get("sender"),request.get("serviceName"));
		return checkClient;
	}
	
	public EntityServer checkServer(Map<String, String> request) {
		EntityServer checkServer = serverService.findByEntityName(request.get("receiver"),request.get("serviceName"));
		return checkServer;
	}
	
	public boolean checkACL(String serviceName,String sender, String receiver, String ip) {
		LinkedHashMap<String, Object> checkACL = aclService.findByPrivilegeAndIp(serviceName, sender, receiver, ip);
		if(null == checkACL || checkACL.isEmpty()) {
			return false;
		}
		return true;
	}
}
