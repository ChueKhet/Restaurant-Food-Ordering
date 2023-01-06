package com.ternion.RFO.service;

import java.util.List;


import com.ternion.RFO.entity.MenuData;
import com.ternion.RFO.entity.SaleDetailData;

import com.ternion.RFO.entity.SaleHeaderData;

public interface SaleService {
	public SaleHeaderData createHeader(SaleHeaderData data);

	public SaleDetailData createDetail(SaleDetailData data);

	public int getTodayMaxSlip();

	public List<SaleHeaderData> findSlipAndTableNo();

	public SaleDetailData updateDetailStatus(SaleDetailData saleData);

	public SaleHeaderData updateHeaderStatus(SaleHeaderData headerData);

}