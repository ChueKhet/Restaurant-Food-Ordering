package com.ternion.RFO.controller;

import com.ternion.RFO.entity.PaymentData;
import com.ternion.RFO.service.PaymentService;
import com.ternion.RFO.service.SaleService;
import com.ternion.RFO.utility.ServerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    
    @Autowired
    SaleService saleService;
    
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
    
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody PaymentData data) {
		String curDate = ServerUtil.getCurrentDate();
		data.setCreatedAt(curDate);
		data.setModifiedAt(curDate);

		PaymentData paymentData = paymentService.create(data);
		
		if (paymentData == null) {
			return ResponseEntity.badRequest().body("Payment already exists!");
		} else {
			//	Needed : need to test whether Header Id exists or not
			saleService.updateStatus(curDate, 1, data.gethId());
		}
		
		return ResponseEntity.ok().body(paymentData);
	}
}
