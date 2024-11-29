package com.example.Invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Invoice.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
