package com.ternion.RFO.service;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;

public interface SaleService {
	public SaleHeaderData createHeader(SaleHeaderData data);
	public SaleDetailData createDetail(SaleDetailData data);
	public int getTodayMaxSlip();
}