package com.ternion.RFO.service;

import com.ternion.RFO.dto.SaleHeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import com.ternion.RFO.repository.SaleDetailRepo;
import com.ternion.RFO.repository.SaleHeaderRepo;
import com.ternion.RFO.utility.ServerUtil;

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
	public List<SaleDetailData> getSaleDetailByHeaderId(int headerId) {
		Optional<SaleHeaderData> saleHeaderOptional= saleHeaderRepo.findById(headerId);
		
		if(saleHeaderOptional.isPresent()){
			List<SaleDetailData> saleDetailList = saleHeaderOptional.get().getDetailList();
			
			return saleDetailList;
		}
		
		return null;
	}

	@Transactional
	@Override
	public void updateStatus(String modifieddata, int status, int headerId) {
		saleHeaderRepo.updateStatus(modifieddata, status, headerId);
	}

	@Transactional
	@Override
	public SaleHeaderData updateOrder(SaleHeaderData header) {
		SaleHeaderData findHeader = saleHeaderRepo.findById(header.getId()).orElse(null);
		String curDate = ServerUtil.getCurrentDate();
		
		if (findHeader == null) {
			return null;
		}
		
		findHeader.setTotalAmount(header.getTotalAmount());
		findHeader.setTableNo(header.getTableNo());
		findHeader.setModifiedAt(curDate);
		
		SaleHeaderData headerData = saleHeaderRepo.save(findHeader);
		
		if (headerData == null) {
			return null;
		} else {
			saleDetailRepo.deleteAllByHeaderId(headerData.getId());
			
			for(int i = 0; i < header.getDetailList().size(); i++) {
				header.getDetailList().get(i).setHeaderData(new SaleHeaderData());
				header.getDetailList().get(i).getHeaderData().setId(headerData.getId());
				header.getDetailList().get(i).setCreatedAt(curDate);
				header.getDetailList().get(i).setModifiedAt(curDate);
				
				createDetail(header.getDetailList().get(i));
			}
		}
		
		return headerData;
	}
}