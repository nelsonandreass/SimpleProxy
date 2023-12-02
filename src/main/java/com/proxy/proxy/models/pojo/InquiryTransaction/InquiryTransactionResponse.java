package com.proxy.proxy.models.pojo.InquiryTransaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InquiryTransactionResponse {
	private String rq_uuid;
	private String rs_datetime;
	private String error_code;
	private String error_message;
	private String amount;
	private String order_id;
	private String ccy;
	private String description;
	private String signature;
	
	public String getRq_uuid() {
		return rq_uuid;
	}
	public void setRq_uuid(String rq_uuid) {
		this.rq_uuid = rq_uuid;
	}
	public String getRs_datetime() {
		return rs_datetime;
	}
	public void setRs_datetime(String rs_datetime) {
		this.rs_datetime = rs_datetime;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	

}
