package com.dw.dynamic.repository;

import com.dw.dynamic.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {
}
