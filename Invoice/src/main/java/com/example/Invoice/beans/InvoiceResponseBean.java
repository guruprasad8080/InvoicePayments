package com.example.Invoice.beans;

public class InvoiceResponseBean {
	private Long id;
	private String message;

	public InvoiceResponseBean(Long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
