package com.ternion.RFO.controller;

import java.util.List;

import com.ternion.RFO.dto.SaleHeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		System.out.println("userid "+header.getUserId());
		String curDate = ServerUtil.getCurrentDate();
		header.setCreatedAt(curDate);
		header.setModifiedAt(curDate);
		
		header.setSlipNo(saleService.getTodayMaxSlip());

		SaleHeaderData headerData = saleService.createHeader(header);
		
		if (headerData == null) {
			return ResponseEntity.badRequest().body("User already exists!");
		} else {
			for(int i = 0; i < header.getDetailList().size(); i++) {
				header.getDetailList().get(i).setHeaderData(new SaleHeaderData());
				header.getDetailList().get(i).getHeaderData().setId(headerData.getId());
				header.getDetailList().get(i).setCreatedAt(curDate);
				header.getDetailList().get(i).setModifiedAt(curDate);
				
				saleService.createDetail(header.getDetailList().get(i));
			}
		}
		
		return ResponseEntity.ok().body(headerData);
	}

	@GetMapping("/headers")
	public ResponseEntity<?> getSaleHeaderList(@RequestParam("userId") int userId){
		List<SaleHeaderDTO> all = saleService.getAllSaleHeaderByUserId(userId);
		return ResponseEntity.ok().body(all);
	}

	@GetMapping("/detail")
	public ResponseEntity<?> getSaleDetail(@RequestParam("headerId") int headerId){
		SaleDetailData detail = saleService.getSaleDetailByHeaderId(headerId);
		return ResponseEntity.ok().body(detail);
	}
}