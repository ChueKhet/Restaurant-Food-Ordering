package com.ternion.RFO.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class SaleHeaderData implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String tableNo;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int totalAmount;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int slipNo;
	
	@Column(nullable = false)
//	@NotBlank(message = "Required")
	private int userId;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int orderStatus;
	
	@Column(nullable = true)
//	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = true)
//	@NotBlank(message = "Required")
	private String modifiedAt;
	
	@Column(nullable = false)
	private int deleteStatus;

	@JsonIgnore
	@OneToMany(mappedBy = "headerData")
	private List<SaleDetailData> detailList;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id", referencedColumnName = "id")
	private PaymentData payment;

	public SaleHeaderData() {
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

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
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

	public List<SaleDetailData> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SaleDetailData> detailList) {
		this.detailList = detailList;
	}

	public PaymentData getPayment() {
		return payment;
	}

	public void setPayment(PaymentData payment) {
		this.payment = payment;
	}
}