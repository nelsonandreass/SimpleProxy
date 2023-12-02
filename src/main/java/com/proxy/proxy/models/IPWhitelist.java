package com.proxy.proxy.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class IPWhitelist implements Serializable{
	@Id
	private String id = UUID.randomUUID().toString();
	private String EntityName;
	private String IPClient;
	
	public IPWhitelist(){
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntityName() {
		return EntityName;
	}
	public void setEntityName(String entityName) {
		EntityName = entityName;
	}

	public String getIPClient() {
		return IPClient;
	}

	public void setIPClient(String iPClient) {
		IPClient = iPClient;
	}
	
	
	
}
