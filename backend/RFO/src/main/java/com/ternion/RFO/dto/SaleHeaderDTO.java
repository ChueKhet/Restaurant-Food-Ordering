package com.ternion.RFO.dto;
import java.util.List;

import com.ternion.RFO.entity.PaymentData;
import com.ternion.RFO.entity.SaleDetailData;
import com.ternion.RFO.entity.SaleHeaderData;

public class SaleHeaderDTO {
    private int id;

    private String tableNo;

    private double totalAmount;

    private int slipNo;

    private String userid;

    private int orderStatus;

    private String createdAt;

    private String modifiedAt;

    private PaymentData payment;
    
    private List<SaleDetailData> detailList;

    public SaleHeaderDTO() {
    }

    public SaleHeaderDTO(SaleHeaderData saleHeader) {
        this.id=saleHeader.getId();
        this.tableNo=saleHeader.getTableNo();
        this.totalAmount=saleHeader.getTotalAmount();
        this.slipNo=saleHeader.getSlipNo();
        this.userid=saleHeader.getUserid();
        this.orderStatus=saleHeader.getOrderStatus();
        this.createdAt=saleHeader.getCreatedAt();
        this.modifiedAt=saleHeader.getModifiedAt();
        this.payment=saleHeader.getPayment();
        this.detailList = saleHeader.getDetailList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(int slipNo) {
        this.slipNo = slipNo;
    }

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public PaymentData getPayment() {
        return payment;
    }

    public void setPayment(PaymentData payment) {
        this.payment = payment;
    }

	public List<SaleDetailData> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SaleDetailData> detailList) {
		this.detailList = detailList;
	}
}