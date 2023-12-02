package com.proxy.proxy.services.pushpayment;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.proxy.proxy.controllers.PushpaymentController;
import com.proxy.proxy.controllers.RequestController;
import com.proxy.proxy.helper.Encoder;
import com.proxy.proxy.models.EntityClient;
import com.proxy.proxy.models.EntityServer;
import com.proxy.proxy.models.pojo.PushPayment.PushPaymentResponse;
import com.proxy.proxy.services.General;

@Service
public class PushPaymentService extends General{
	Logger logger = LoggerFactory.getLogger(PushPaymentService.class);
	private Encoder encode;
	
	public String handle(LinkedHashMap<String, Object> request, EntityServer serverObj, EntityClient clientObj) {
		LocalDate date = LocalDate.now();
		String auth = clientObj.getUserName()+ ":" + clientObj.getPassword();
		byte[] encodedAuth =  Base64.getEncoder().encode(auth.getBytes());
	
		
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(new MediaType("application", "x-www-form-urlencoded"));
		headers.set("Authorization", "Basic " + new String(encodedAuth));
		
		String plainSignature = "##" + request.get("rq_uuid").toString() + 
				"##" + request.get("sender").toString() + 
				"##" + request.get("product_code").toString() + 
				"##" + request.get("order_id").toString() + 
				"##" + request.get("amount").toString() +
				"##PUSHTOPAY##" + 
				clientObj.getSecretKey1()+"##";
		String toUpper = plainSignature.toUpperCase();
		String signatureHashed = encode.hashString(toUpper);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
	      map.add("rq_uuid", request.get("rq_uuid").toString());
	      map.add("rq_datetime", date.toString());
	      map.add("comm_code", request.get("sender").toString());
	      map.add("order_id", request.get("order_id").toString());
	      map.add("customer_id", request.get("customer_id").toString());
	      map.add("product_code", request.get("product_code").toString());
	      map.add("amount", request.get("amount").toString());
	      map.add("description", "Payment "+ request.get("order_id").toString());
	      map.add("signature", signatureHashed);
	      
	      HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
	 	 
	      ResponseEntity<String> responseEntity = this.rest.exchange(
	                serverObj.getServiceURL().toString(),
	                HttpMethod.POST,
	                requestEntity,
	                String.class);
			
	      
	    
	      return responseEntity.getBody();
		
	}
	
}
