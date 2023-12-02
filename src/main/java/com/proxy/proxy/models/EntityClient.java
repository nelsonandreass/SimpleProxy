package com.proxy.proxy.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EntityClient implements Serializable {
	@Id
	private String ID = UUID.randomUUID().toString();
	private String EntityName;
	private String ServiceURL;
	private String ServiceName;
	private String UserName;
	private String Password;
	private String SecretKey1;
	private String SecretKey2;
	private String SecretKey3;
	
	public EntityClient(){}

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

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSecretKey1() {
		return SecretKey1;
	}

	public void setSecretKey1(String secretKey1) {
		SecretKey1 = secretKey1;
	}

	public String getSecretKey2() {
		return SecretKey2;
	}

	public void setSecretKey2(String secretKey2) {
		SecretKey2 = secretKey2;
	}

	public String getSecretKey3() {
		return SecretKey3;
	}

	public void setSecretKey3(String secretKey3) {
		SecretKey3 = secretKey3;
	}

	
	
	
}
