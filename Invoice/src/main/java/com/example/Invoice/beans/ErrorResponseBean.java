package com.example.Invoice.beans;

import org.springframework.http.HttpStatus;

public class ErrorResponseBean {
	
	private String errorMessage;
	private HttpStatus errorCode;
	
	
	
	public ErrorResponseBean(String errorMessage, HttpStatus errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public HttpStatus getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
