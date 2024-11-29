package com.example.Invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Invoice.beans.ErrorResponseBean;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(InvoiceExceptions.class)
	public ResponseEntity<ErrorResponseBean> handleInvoiceException(InvoiceExceptions inException){
		ErrorResponseBean bean=new ErrorResponseBean(inException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ErrorResponseBean>(bean,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
