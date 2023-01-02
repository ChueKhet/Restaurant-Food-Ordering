package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class SaleDetailData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@Column(nullable = false)
//	@NotBlank(message = "Required")
//	private int headerId;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int menuId;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int qty;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int price;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int totalPrice;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String remark;
	
	@Column(nullable = true)
//	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = true)
//	@NotBlank(message = "Required")
	private String modifiedAt;
	
	@Column(nullable = false)
	private int deleteStatus;
	
	@ManyToOne
    @JoinColumn(name="headerId", nullable = true)
	private SaleHeaderData headerData;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
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

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@JsonBackReference
	public SaleHeaderData getHeaderData() {
		return headerData;
	}

	public void setHeaderData(SaleHeaderData headerData) {
		this.headerData = headerData;
	}
}