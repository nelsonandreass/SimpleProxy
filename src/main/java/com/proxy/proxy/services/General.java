package com.proxy.proxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class General {
	protected RestTemplate rest;
	
	@Autowired
	public void call(RestTemplate rest){
		this.rest = rest;
	}
}
