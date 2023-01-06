package com.ternion.RFO.service;

import java.util.List;
import com.ternion.RFO.entity.MenuData;
import com.ternion.RFO.dto.SaleHeaderDTO;
import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;
import java.util.List;

public interface SaleService {
	public SaleHeaderData createHeader(SaleHeaderData data);
<<<<<<< HEAD

	public SaleDetailData createDetail(SaleDetailData data);

	public int getTodayMaxSlip();

	public List<SaleHeaderData> findSlipAndTableNo();

	public SaleDetailData updateDetailStatus(SaleDetailData saleData);

	public SaleHeaderData updateHeaderStatus(SaleHeaderData headerData);

	public List<SaleHeaderDTO> getAllSaleHeaderByUserId(int userId);

	SaleDetailData getSaleDetailByHeaderId(int headerId);
=======
	
	public SaleDetailData createDetail(SaleDetailData data);
	
	public List<SaleHeaderDTO> getAllSaleHeaderByUserId(int userId);
	
	public int getTodayMaxSlip();

	public SaleDetailData getSaleDetailByHeaderId(int headerId);
	
	public void updateStatus(String modifieddata, int status, int headerId);
>>>>>>> 028491b404920f338c32d9627f7815e6be24a054
}