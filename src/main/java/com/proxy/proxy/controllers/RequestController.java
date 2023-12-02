package com.proxy.proxy.controllers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxy.proxy.helper.LogWriter;
import com.proxy.proxy.models.EntityACL;
import com.proxy.proxy.models.EntityClient;
import com.proxy.proxy.models.EntityServer;
import com.proxy.proxy.models.pojo.DataResponse;
import com.proxy.proxy.models.pojo.DefaultResponse;
import com.proxy.proxy.models.pojo.PushPayment.PushPaymentResponse;
import com.proxy.proxy.models.pojo.PushPayment.PushPaymentResponseFailed;
import com.proxy.proxy.services.RequestService;

import jakarta.servlet.http.HttpServletRequest;
import redis.clients.jedis.Jedis;

@RestController
public class RequestController{
	
	protected RestTemplate rest;
	protected LogWriter logWriter;
	protected DefaultResponse response = new DefaultResponse();
	protected DataResponse dataResponse = new DataResponse();
	
	protected EntityClient ClientObj;
	protected EntityServer ServerObj;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	protected ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	public void call(RestTemplate rest){
		this.rest = rest;
		Jedis jedis = new Jedis("localhost", 6379);
		logWriter = new LogWriter();
		jedis.close();
	}
	
	
	protected void setResponse(String erorrCode,String errorDescription){
		response.setErrorCode(erorrCode);
		response.setErrorDescription(errorDescription);
	}
	
	protected void setResponseData(String erorrCode,String errorDescription, Object data) {
		dataResponse.setErrorCode(erorrCode);
		dataResponse.setErrorDescription(errorDescription);
		dataResponse.setResult(data);
	}
	
	public boolean validate(int check){
		if(1 == check) {
			this.setResponse("400", "Cannot Map Client Service");
			return false;
			//return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.BAD_REQUEST);
		}
		if(2 == check) {
			this.setResponse("400", "Cannot Map Server Service");
			return false;
			//return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.BAD_REQUEST);
		}
		if(3 == check) {
			this.setResponse("400", "Client Doesn't have privilege or IP rejected");
			return false;
			//return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.BAD_REQUEST);
		return true;
	}
	
	@SuppressWarnings("ConstantConditions")
	protected String fetchClientIpAddr() {
	    HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
	    String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
	    if (ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";
	    Assert.isTrue(ip.chars().filter($ -> $ == '.').count() == 3, "Illegal IP: " + ip);
	    return ip;
	}
	
	public int checkRequest(Map<String, String> request, String ip) {
		EntityClient checkClient = requestService.checkClient(request);
		EntityServer checkServer= requestService.checkServer(request);
		boolean checkAcl = requestService.checkACL(request.get("serviceName"), request.get("sender") , request.get("receiver"), ip);
		ClientObj = checkClient;
		ServerObj = checkServer;
		if(null == checkClient) {
			return 1;
		}
		if(null == checkServer) {
			return 2;
		}
		if(false == checkAcl) {
			return 3;
		}
		return 0;
		
	} 	
	
	
	public void writeLog(String request, String response) {
		this.logWriter.Writer("Request:"+request + "\n" + "Response" + response +"||");
	}
}
