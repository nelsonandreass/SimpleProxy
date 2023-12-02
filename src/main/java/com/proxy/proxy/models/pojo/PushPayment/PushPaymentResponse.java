package com.proxy.proxy.models.pojo.PushPayment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PushPaymentResponse {
	@JsonProperty("error_code")
	private String errorCode;
	@JsonProperty("error_message")
	private String errorDescription;
	@JsonProperty("trx_id")
	private String trxId;
	@JsonProperty("QRCode")
	private String qr;
	
	public PushPaymentResponse() {
		
	}
	
	public PushPaymentResponse(String errorCode, String errorDescription, String trxid, String qrcode) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.trxId = trxid;
		if("0000".equals(errorCode)) {
			this.qr = qrcode;
		}
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		
		this.qr = qr;
		
	}
	
	
}
