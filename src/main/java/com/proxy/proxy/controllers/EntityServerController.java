package com.proxy.proxy.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.proxy.proxy.models.EntityServer;
import com.proxy.proxy.models.pojo.DefaultResponse;
import com.proxy.proxy.services.EntityServerService;

@RestController
public class EntityServerController extends RequestController{
	@Autowired
	private EntityServerService serverService;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping(value = "/entityserver")
	public ResponseEntity<?>save(@RequestBody LinkedHashMap<String, Object> request){
		boolean entityServer = serverService.save(mapper.convertValue(request, EntityServer.class));
		if(true == entityServer) {
			this.setResponse("200", "Success");
			return new ResponseEntity<DefaultResponse>(this.response , HttpStatus.OK);
		}
		this.setResponse("400", "Failed");
		return new ResponseEntity<DefaultResponse>(this.response , HttpStatus.BAD_REQUEST);
	}
}
