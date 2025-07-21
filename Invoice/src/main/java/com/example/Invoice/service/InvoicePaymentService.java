package com.example.Invoice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Invoice.beans.OverDueBean;
import com.example.Invoice.entity.Invoice;
import com.example.Invoice.entity.Payments;
import com.example.Invoice.exceptions.InvoiceExceptions;
import com.example.Invoice.repositories.InvoiceRepository;
import com.example.Invoice.repositories.PaymentsRepository;

/**
 * @author Guruprasad
 * */

@Service
public class InvoicePaymentService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private PaymentsRepository paymentRepository;

	/*
	 * createInvoice() 
	 * method is responsible to persist the details on Invoice
	 */
	public Invoice createInvoice(Double amount, LocalDate dueDate) {
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		invoice.setDueDate(dueDate);
		return invoiceRepository.save(invoice);
	}
	
	/*
	 * getAllInvoices() 
	 * method to load all the Invoices
	 */
	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}
	
	/*
	 * payInvoice() 
	 * method is responsible to persist the payment amount which
	 * invoice has been created
	 */
	public void payInvoice(Long invoiceId, Double paymentAmount) {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new InvoiceExceptions("Invoice not found"));
		double newPaidAmount = invoice.getPaidAmount() + paymentAmount;
		invoice.setPaidAmount(newPaidAmount);

		if (newPaidAmount >= invoice.getAmount()) {
			invoice.setStatus(Invoice.Status.PAID);
		}
		invoiceRepository.save(invoice);

		Payments payment = new Payments();
		payment.setInvoice(invoice);
		payment.setAmount(paymentAmount);
		paymentRepository.save(payment);
	}

	
	/*
	 * processOverdue() 
	 * method is responsible to check the payment and overdue
	 * date/days and add the late fees.
	 */
	public void processOverdue(OverDueBean overDueBean) {
		List<Invoice> allInvoices = invoiceRepository.findAll();
		List<Invoice> overdueInvoices = new ArrayList<>();
		LocalDate cutoffDate = LocalDate.now().minusDays(overDueBean.getOverdueDays());
		for (Invoice invoice : allInvoices) {
		    if (invoice.getStatus() == Invoice.Status.PENDING && invoice.getDueDate().isBefore(cutoffDate)) {
		        overdueInvoices.add(invoice);
		    }
		}

		for (Invoice invoice : overdueInvoices) {
			if (invoice.getPaidAmount() > 0) {
				double remainingAmount = invoice.getAmount() - invoice.getPaidAmount();
				invoice.setStatus(Invoice.Status.PAID);
				Invoice newInvoice = createInvoice(remainingAmount + overDueBean.getLateFee(),
						LocalDate.now().plusDays(overDueBean.getOverdueDays()));
				newInvoice.setPaidAmount(invoice.getPaidAmount());
				invoiceRepository.save(newInvoice);
			} else {
				invoice.setStatus(Invoice.Status.VOID);
				createInvoice(invoice.getAmount() + overDueBean.getLateFee(),
						LocalDate.now().plusDays(overDueBean.getOverdueDays()));
			}
			invoiceRepository.save(invoice);
		}
	}

}
