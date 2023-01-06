package com.ternion.RFO.service;

import com.ternion.RFO.entity.PaymentData;

import java.util.List;

public interface PaymentService {
    List<PaymentData> getAllPayment();
    public PaymentData create(PaymentData data);

    PaymentData getById(int id);
}
