package com.ternion.RFO.service;

import java.util.List;

import com.ternion.RFO.entity.SaleDetailData;

import com.ternion.RFO.entity.SaleHeaderData;

public interface SaleService {
	public SaleHeaderData createHeader(SaleHeaderData data);
	public SaleDetailData createDetail(SaleDetailData data);
	public int getTodayMaxSlip();
	public List<SaleHeaderData> findSlipAndTableNo();
	public List<String> getOrderDetailbyHeaderId(int header_id);
}