package com.ternion.RFO.service;

import com.ternion.RFO.dto.SaleHeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import com.ternion.RFO.repository.SaleDetailRepo;
import com.ternion.RFO.repository.SaleHeaderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public List<SaleHeaderDTO> getAllSaleHeaderByUserId(int userId) {
		List<SaleHeaderData> saleHeaderList=saleHeaderRepo.findByUserId(userId);
		List<SaleHeaderDTO> saleHeaderDTOs=new ArrayList<>();
		for(SaleHeaderData saleHeader:saleHeaderList){
			SaleHeaderDTO saleHeaderDTO=new SaleHeaderDTO(saleHeader);
			saleHeaderDTOs.add(saleHeaderDTO);
		}
		return saleHeaderDTOs;
	}

	@Override
	public int getTodayMaxSlip() {
		return saleHeaderRepo.getTodayMaxSlip();
	}

	@Override
	public SaleDetailData getSaleDetailByHeaderId(int headerId) {
		Optional<SaleHeaderData> saleHeaderOptional= saleHeaderRepo.findById(headerId);
		if(saleHeaderOptional.isPresent()){
			List<SaleDetailData> saleDetailList= saleHeaderOptional.get().getDetailList();
			if(saleDetailList.size()>0){
				return saleDetailList.get(saleDetailList.size()-1);
			}
		}
		return null;
	}

	@Transactional
	@Override
	public void updateStatus(String modifieddata, int status, int headerId) {
		saleHeaderRepo.updateStatus(modifieddata, status, headerId);
	}
}