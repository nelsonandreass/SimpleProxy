package com.proxy.proxy.controllers;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxy.proxy.models.EntityClient;
import com.proxy.proxy.models.pojo.DataResponse;
import com.proxy.proxy.models.pojo.DefaultResponse;
import com.proxy.proxy.services.EntityClientService;

@RestController
public class EntityClientController extends RequestController{
	Logger logger = LoggerFactory.getLogger(PushpaymentController.class);
	
	@Autowired
	private EntityClientService clientService;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping(value="/entityclient")
	public ResponseEntity<?> save(@RequestBody LinkedHashMap<String, Object> request){
		boolean ec = clientService.save(mapper.convertValue(request, EntityClient.class));
		if(ec == true) {
			this.setResponse("200", "Success");	
			return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.OK);
		}
		
		this.setResponse("400", "Failed");
		return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value="/entityclient/{id}")
	//@Cacheable(value = "EntityClient", key = "#id")
	public ResponseEntity<?> findById(@PathVariable String id){
		//System.out.println("From Db");
		EntityClient client = clientService.findById(id);
		this.setResponseData("200", "Success", client);
		return new ResponseEntity<DataResponse>(this.dataResponse,HttpStatus.OK);
	}
}
