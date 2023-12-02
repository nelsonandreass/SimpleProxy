package com.proxy.proxy.controllers;

import java.time.LocalDate;
import java.util.Base64;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.proxy.proxy.models.IPWhitelist;
import com.proxy.proxy.models.pojo.DataResponse;
import com.proxy.proxy.models.pojo.DefaultResponse;
import com.proxy.proxy.models.pojo.PushPayment.PushPaymentResponse;
import com.proxy.proxy.services.RateLimiterService;
import com.proxy.proxy.services.pushpayment.PushPaymentService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import com.proxy.proxy.helper.Encoder ;

@RestController
public class PushpaymentController extends RequestController{
	private	Encoder encode; 
	Logger logger = LoggerFactory.getLogger(PushpaymentController.class);
	
	@Autowired
	private PushPaymentService pushpaymentService;
	
	@Autowired
	private RateLimiterService limiter;
	
	//Client hit to server, in this case hit ESPAY as Payment Gateway
	@PostMapping(value = "/v1/pushpayment")
	public ResponseEntity<?> pushpayment(@RequestBody LinkedHashMap<String, Object> request){
		String clientIp = this.fetchClientIpAddr();
		Map<String, String> pair= new HashMap<>();
		pair.put("sender", request.get("sender").toString());
		pair.put("receiver", "Espay");
		pair.put("serviceName","pushpayment");
		if(!limiter.allowRequest(request.get("sender").toString(), clientIp)) {
			this.setResponse("429", "Request limit exceeded. Please try again later.");
			return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.TOO_MANY_REQUESTS);
		}
		boolean validateRequest = this.validate(this.checkRequest(pair, clientIp));
		if(false == validateRequest) {
			return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.OK);
		}
		String responseCall = pushpaymentService.handle(request, this.ServerObj, this.ClientObj);
		this.writeLog(request.toString(), responseCall);
		PushPaymentResponse pushPaymentResponse = null;
		try {
			pushPaymentResponse = mapper.readValue(responseCall, PushPaymentResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<PushPaymentResponse>(pushPaymentResponse,HttpStatus.OK);
	}
}
