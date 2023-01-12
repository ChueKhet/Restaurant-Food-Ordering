package com.ternion.RFO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import com.ternion.RFO.service.KitchenService;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {
	@Autowired
	KitchenService kitchenService;
	
	@GetMapping("/order/all")
	List<SaleHeaderData> getSlipAndTableNo() {
		return kitchenService.findSlipAndTableNo();
	}
	
	@PutMapping("/order/status/served")
	public ResponseEntity<?> update(@RequestBody SaleDetailData saleData) {
		SaleDetailData updatedSaleData = kitchenService.updateDetailStatus(saleData);
		if (updatedSaleData == null) {
			return ResponseEntity.badRequest().body("Status update fail");
		}
		
		return ResponseEntity.ok(updatedSaleData);
	}

	@PutMapping("/order/status/done")
	public ResponseEntity<?> update(@RequestBody SaleHeaderData headerData) {
		SaleHeaderData updatedHeaderData = kitchenService.updateHeaderStatus(headerData);
		if (updatedHeaderData == null) {
			return ResponseEntity.badRequest().body("Status update fail");
		}
		
		return ResponseEntity.ok(updatedHeaderData);
	}
}