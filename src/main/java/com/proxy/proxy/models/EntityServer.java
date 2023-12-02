package com.proxy.proxy.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EntityServer implements Serializable{
	@Id
	private String ID = UUID.randomUUID().toString();
	private String EntityName;
	private String ServiceURL;
	private String ServiceName;
	
	public EntityServer() {}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEntityName() {
		return EntityName;
	}

	public void setEntityName(String entityName) {
		EntityName = entityName;
	}

	public String getServiceURL() {
		return ServiceURL;
	}

	public void setServiceURL(String serviceURL) {
		ServiceURL = serviceURL;
	}

	public String getServiceName() {
		return ServiceName;
	}

	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	
	
}
