package com.example.Invoice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Invoice.beans.OverDueBean;
import com.example.Invoice.entity.Invoice;
import com.example.Invoice.repositories.InvoiceRepository;
import com.example.Invoice.service.InvoicePaymentService;

@ExtendWith(MockitoExtension.class)
public class InvoicePaymentServiceTests {

    @InjectMocks
    private InvoicePaymentService invoicePaymentService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    public void testProcessOverdueWithPartialPayment() {
        // Given
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setAmount(1000.0);
        invoice.setPaidAmount(500.0);
        invoice.setDueDate(LocalDate.now().minusDays(10));
        invoice.setStatus(Invoice.Status.PENDING);

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        OverDueBean overDueBean = new OverDueBean();
        overDueBean.setOverdueDays(5);
        overDueBean.setLateFee(100.0);

        when(invoiceRepository.findAll()).thenReturn(invoices);
        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        invoicePaymentService.processOverdue(overDueBean);

        // Then
        verify(invoiceRepository, times(3)).save(any(Invoice.class));
    }
}
