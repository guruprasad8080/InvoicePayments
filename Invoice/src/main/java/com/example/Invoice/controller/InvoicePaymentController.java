package com.example.Invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Invoice.beans.InvoiceRequestBean;
import com.example.Invoice.beans.InvoiceResponseBean;
import com.example.Invoice.beans.OverDueBean;
import com.example.Invoice.beans.PaymentsRequestBean;
import com.example.Invoice.entity.Invoice;
import com.example.Invoice.service.InvoicePaymentService;

/**
 * @author Guruprasad
 * */

@RestController
@RequestMapping("/api/invoices")
public class InvoicePaymentController {
	@Autowired
	private InvoicePaymentService invoicePayService;

	@PostMapping
	public ResponseEntity<?> createInvoice(@RequestBody InvoiceRequestBean invReqBean) {
		Invoice invoice = invoicePayService.createInvoice(invReqBean.getAmount(),invReqBean.getDueDate());
	    InvoiceResponseBean response = new InvoiceResponseBean(invoice.getId(), "Invoice created successfully");
	    return ResponseEntity.status(201).body(response);
	}

	@GetMapping
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		return ResponseEntity.ok(invoicePayService.getAllInvoices());
	}

	@PostMapping("/{id}/payments")
	public ResponseEntity<?> payInvoice(@PathVariable Long id, @RequestBody PaymentsRequestBean payReqBean) {
		invoicePayService.payInvoice(id, payReqBean.getAmount());
		return ResponseEntity.ok("Payment completed successfully.");
	}

	@PostMapping("/process-overdue")
	public ResponseEntity<?> processOverdue(@RequestBody OverDueBean overDueBean) {
		invoicePayService.processOverdue(overDueBean);
		return ResponseEntity.ok("Overdue penalty has been added.!");
	}

}
