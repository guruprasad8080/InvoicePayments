package com.example.Invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
