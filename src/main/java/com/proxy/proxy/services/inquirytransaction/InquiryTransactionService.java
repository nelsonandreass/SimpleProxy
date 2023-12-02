package com.proxy.proxy.services.inquirytransaction;

import java.time.LocalDate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.proxy.proxy.models.EntityClient;
import com.proxy.proxy.models.EntityServer;
import com.proxy.proxy.services.General;

@Service
public class InquiryTransactionService extends General{
	
	public String handle(MultiValueMap<String, String> request , EntityServer serverObj, EntityClient clientObj) {
		LocalDate date = LocalDate.now();
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
	      map.add("rq_uuid", request.getFirst("rq_uuid"));
	      map.add("rq_datetime", date.toString());
	      map.add("password", request.getFirst("password"));
	      map.add("signature", request.getFirst("signature"));
	      map.add("member_code", request.getFirst("member_code"));
	      map.add("comm_code", request.getFirst("comm_code"));
	      map.add("order_id", request.getFirst("order_id"));
	      
	      HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, null);
	 	 
	      ResponseEntity<String> responseEntity = this.rest.exchange(
	                serverObj.getServiceURL().toString(),
	                HttpMethod.POST,
	                requestEntity,
	                String.class);
	    
	      return responseEntity.getBody();
	}

}
