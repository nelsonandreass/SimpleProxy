package com.proxy.proxy.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.proxy.proxy.models.pojo.DefaultResponse;
import com.proxy.proxy.services.inquirytransaction.InquiryTransactionService;

import InquiryTransaction.InquiryTransactionResponse;

@RestController
public class InquiryController extends RequestController{
	@Autowired
	private InquiryTransactionService inquiryService;
	
	@PostMapping(value = "/api/inquiry")
	public ResponseEntity<?>inquiry(@RequestBody MultiValueMap<String, String> request){
		Map<String, String> pair = new HashMap<>();
		String clientIp = this.fetchClientIpAddr();
		String sender = request.getFirst("order_id").isBlank() ? request.getFirst("order_id") : "Espay";
		pair.put("sender", sender);
		pair.put("receiver", request.getFirst("comm_code"));
		pair.put("serviceName", "InquiryTransaction");
		boolean validateRequest = this.validate(this.checkRequest(pair, clientIp));
		if(false == validateRequest) {
			return new ResponseEntity<DefaultResponse>(this.response,HttpStatus.OK);
		}
		
		String responseCall = inquiryService.handle(request, this.ServerObj, this.ClientObj);
		this.writeLog(request.toString(), responseCall);
		InquiryTransactionResponse inquiryResponse = null;
		try {
			inquiryResponse = mapper.readValue(responseCall, InquiryTransactionResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<InquiryTransactionResponse>(inquiryResponse,HttpStatus.OK);
	}
}
