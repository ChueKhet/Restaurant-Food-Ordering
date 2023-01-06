package com.ternion.RFO.service;

import com.ternion.RFO.entity.PaymentData;
import com.ternion.RFO.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepo paymentRepo;
    @Override
    public List<PaymentData> getAllPayment() {
        return paymentRepo.findAll();
    }

    @Override
    public PaymentData getById(int id) {
        Optional<PaymentData> optional= paymentRepo.findById(id);
        return optional.isPresent()?optional.get():null;
    }

	@Override
	public PaymentData create(PaymentData data) {
		
		return paymentRepo.save(data);
	}
}
