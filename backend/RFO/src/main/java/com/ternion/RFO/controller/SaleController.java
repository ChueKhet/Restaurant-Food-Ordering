package com.ternion.RFO.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.MenuData;
import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import com.ternion.RFO.service.SaleService;
import com.ternion.RFO.utility.ServerUtil;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
	@Autowired
	SaleService saleService;
	
	@PostMapping("/order/confirm")
	public ResponseEntity<?> create(@RequestBody SaleHeaderData header) {
		
		String curDate = ServerUtil.getCurrentDate();
		header.setCreatedAt(curDate);
		header.setModifiedAt(curDate);
		
		header.setSlipNo(saleService.getTodayMaxSlip());

		SaleHeaderData headerData = saleService.createHeader(header);
//		SaleDetailData detailData = new SaleDetailData();
		
		if (headerData == null) {
			return ResponseEntity.badRequest().body("User already exists!");
		} else {
//			SaleDetailData detail = new SaleDetailData();
			
			for(int i = 0; i < header.getDetailList().size(); i++) {
				header.getDetailList().get(i).setHeaderData(new SaleHeaderData());
				header.getDetailList().get(i).getHeaderData().setId(headerData.getId());
				
				saleService.createDetail(header.getDetailList().get(i));
				
				/*
				detail = header.getDetailList().get(i);
				detail.getHeaderData().setId(headerData.getId());
				
				detailData = saleService.createDetail(detail);
				
				if (detailData != null) {
					headerData.getDetailList().add(detailData);
				}
				*/
			}
		}
		
		return ResponseEntity.ok().body(headerData);
	}
	
	@GetMapping("kitchen/order/all")
	List<SaleHeaderData> getSlipAndTableNo() {
		return saleService.findSlipAndTableNo();
	}
	
	@PutMapping("/kitchen/order/status/served")
	public ResponseEntity<?> update(@RequestBody SaleDetailData saleData) {
		SaleDetailData updatedSaleData = saleService.updateDetailStatus(saleData);
		if (updatedSaleData == null) {
			return ResponseEntity.badRequest().body("Status update fail");
		}
		
		return ResponseEntity.ok(updatedSaleData);
	}

	@PutMapping("/kitchen/order/status/done")
	public ResponseEntity<?> update(@RequestBody SaleHeaderData headerData) {
		SaleHeaderData updatedHeaderData = saleService.updateHeaderStatus(headerData);
		if (updatedHeaderData == null) {
			return ResponseEntity.badRequest().body("Status update fail");
		}
		
		return ResponseEntity.ok(updatedHeaderData);
	}
}