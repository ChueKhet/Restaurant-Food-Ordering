package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class SaleDetailData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public SaleDetailData() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@Column(nullable = false)
//	@NotBlank(message = "Required")
//	private int headerId;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private int menuId;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private int qty;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private double price;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private double totalPrice;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String remark;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String modifiedAt;
	
	@ManyToOne
    @JoinColumn(name="headerId", nullable = true)
	private SaleHeaderData headerData;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String orderStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getHeaderId() {
//		return headerId;
//	}
//
//	public void setHeaderId(int headerId) {
//		this.headerId = headerId;
//	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@JsonBackReference
	public SaleHeaderData getHeaderData() {
		return headerData;
	}

	public void setHeaderData(SaleHeaderData headerData) {
		this.headerData = headerData;
	}
	
	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
}