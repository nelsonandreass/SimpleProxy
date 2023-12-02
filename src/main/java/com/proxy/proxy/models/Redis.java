package com.proxy.proxy.models;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@RedisHash("Redis")
public class Redis {

	@Id
	private String id;
	private String name;
	private int price;
	
	
}
