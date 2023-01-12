package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import com.ternion.RFO.repository.KitchenRepo;
import com.ternion.RFO.repository.SaleDetailRepo;
import com.ternion.RFO.repository.SaleHeaderRepo;

@Service
public class KitchenServiceImpl implements KitchenService {
	@Autowired
	SaleHeaderRepo saleHeaderRepo;
	
	@Autowired
	SaleDetailRepo saleDetailRepo;
	
	@Autowired
	KitchenRepo kitchenRepo;
	
	@Override
	public List<SaleHeaderData> findSlipAndTableNo() {
		return kitchenRepo.findSlipAndTableNo();
	}

	@Override
	public SaleDetailData updateDetailStatus(SaleDetailData saleData) {
		SaleDetailData findSaleData = saleDetailRepo.findById(saleData.getId()).orElse(null);
		
		if (findSaleData == null) {
			return null;
		}

		findSaleData.setOrderStatus(saleData.getOrderStatus());
		
		return saleDetailRepo.save(findSaleData);
	}

	@Override
	public SaleHeaderData updateHeaderStatus(SaleHeaderData headerData) {
		SaleHeaderData findHeaderData = saleHeaderRepo.findById(headerData.getId()).orElse(null);
		
		if (findHeaderData == null) {
			return null;
		}

		findHeaderData.setOrderStatus(headerData.getOrderStatus());
		
		return saleHeaderRepo.save(findHeaderData);
	}
}