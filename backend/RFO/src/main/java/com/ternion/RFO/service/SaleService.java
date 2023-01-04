package com.ternion.RFO.service;

import com.ternion.RFO.dto.SaleHeaderDTO;
import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;

import java.util.List;

public interface SaleService {
	public SaleHeaderData createHeader(SaleHeaderData data);
	public SaleDetailData createDetail(SaleDetailData data);
	public List<SaleHeaderDTO> getAllSaleHeaderByUserId(int userId);
	public int getTodayMaxSlip();

	SaleDetailData getSaleDetailByHeaderId(int headerId);
}