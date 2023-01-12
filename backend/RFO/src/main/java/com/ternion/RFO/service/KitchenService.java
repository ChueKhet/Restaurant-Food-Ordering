package com.ternion.RFO.service;

import java.util.List;

import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;

public interface KitchenService {
	public List<SaleHeaderData> findSlipAndTableNo();

	public SaleDetailData updateDetailStatus(SaleDetailData saleData);

	public SaleHeaderData updateHeaderStatus(SaleHeaderData headerData);
}