package com.ternion.RFO.repository;

import com.ternion.RFO.entity.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentData, Integer> {
}
