package com.example.Invoice.beans;

import java.time.LocalDate;


public class InvoiceRequestBean {
	private Double amount;
    private LocalDate dueDate;
    
    
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
    
    
    

}
