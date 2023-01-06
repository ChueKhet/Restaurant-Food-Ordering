package com.ternion.RFO.controller;

import com.ternion.RFO.entity.PaymentData;
import com.ternion.RFO.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @GetMapping
    ResponseEntity<?> getAllPayments(){
        List<PaymentData> payments=paymentService.getAllPayment();
        return ResponseEntity.ok().body(payments);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable int id){
        PaymentData payment=paymentService.getById(id);
        return ResponseEntity.ok().body(payment);
    }
}
