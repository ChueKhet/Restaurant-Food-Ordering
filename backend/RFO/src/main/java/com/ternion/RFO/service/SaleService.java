package com.ternion.RFO.service;

import java.util.List;

//import com.ternion.RFO.dto.SaleHeaderDTO;
import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;

public interface SaleService {
	public SaleHeaderData createHeader(SaleHeaderData data);

	public SaleDetailData createDetail(SaleDetailData data);

	public int getTodayMaxSlip();

	public List<SaleDetailData> getSaleDetailByHeaderId(int headerId);

//	public List<SaleHeaderData> getAllSaleHeaderByUserId(int userId);
	
	public List<SaleHeaderData> getAllOrderList();
	
	public void updateStatus(String modifieddata, int status, int headerId);
	
	public SaleHeaderData updateOrder(SaleHeaderData header);
}