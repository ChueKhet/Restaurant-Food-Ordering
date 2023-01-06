package com.ternion.RFO.dto;
import com.ternion.RFO.entity.PaymentData;
import com.ternion.RFO.entity.SaleHeaderData;

public class SaleHeaderDTO {
    private int id;

    private String tableNo;

    private double totalAmount;

    private int slipNo;

    private int userId;

    private int orderStatus;

    private String createdAt;

    private String modifiedAt;

    private int deleteStatus;

    private PaymentData payment;

    public SaleHeaderDTO() {
    }

    public SaleHeaderDTO(SaleHeaderData saleHeader) {
        this.id=saleHeader.getId();
        this.tableNo=saleHeader.getTableNo();
        this.totalAmount=saleHeader.getTotalAmount();
        this.slipNo=saleHeader.getSlipNo();
        this.userId=saleHeader.getUserId();
        this.orderStatus=saleHeader.getOrderStatus();
        this.createdAt=saleHeader.getCreatedAt();
        this.modifiedAt=saleHeader.getModifiedAt();
        this.payment=saleHeader.getPayment();
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public PaymentData getPayment() {
        return payment;
    }

    public void setPayment(PaymentData payment) {
        this.payment = payment;
    }
}
