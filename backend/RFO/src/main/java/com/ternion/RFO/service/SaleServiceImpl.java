package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import com.ternion.RFO.repository.SaleDetailRepo;
import com.ternion.RFO.repository.SaleHeaderRepo;

@Service
public class SaleServiceImpl implements SaleService{
	@Autowired
	SaleHeaderRepo saleHeaderRepo;
	
	@Autowired
	SaleDetailRepo saleDetailRepo;

	@Override
	public SaleHeaderData createHeader(SaleHeaderData data) {
		return saleHeaderRepo.save(data);
	}

	@Override
	public SaleDetailData createDetail(SaleDetailData data) {
		return saleDetailRepo.save(data);
	}
	
	@Override
	public int getTodayMaxSlip() {
		return saleHeaderRepo.getTodayMaxSlip();
	}

	@Override
	public List<SaleHeaderData> findSlipAndTableNo() {
		return saleHeaderRepo.findSlipAndTableNo();
	}

	@Override
	public List<String> getOrderDetailbyHeaderId(int header_id) {
		return saleDetailRepo.findByHeaderId(header_id);
	}

	
}