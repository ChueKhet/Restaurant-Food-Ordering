package com.ternion.RFO.controller;

import java.util.List;

//import com.ternion.RFO.dto.SaleHeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	@PostMapping("/order/update")
	public ResponseEntity<?> update(@RequestBody SaleHeaderData header) {
		SaleHeaderData updatedSale = saleService.updateOrder(header);
		
		if (updatedSale == null) {
			return ResponseEntity.badRequest().body("Menu cannot be updated");
		}
		
		return ResponseEntity.ok(updatedSale);
	}

	@GetMapping("/headers")
	public ResponseEntity<?> getSaleHeaderList(){		//		@RequestParam("userId") int userId
		List<SaleHeaderData> all = saleService.getAllOrderList();
		
		return ResponseEntity.ok().body(all);
	}

	@GetMapping("/detail")
	public ResponseEntity<?> getSaleDetail(@RequestParam("headerId") int headerId){
		List<SaleDetailData> detail = saleService.getSaleDetailByHeaderId(headerId);
		
		return ResponseEntity.ok().body(detail);
	}
}