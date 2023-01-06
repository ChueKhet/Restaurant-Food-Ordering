package com.ternion.RFO.service;

import com.ternion.RFO.entity.PaymentData;

import java.util.List;

public interface PaymentService {
    List<PaymentData> getAllPayment();

    PaymentData getById(int id);
}
