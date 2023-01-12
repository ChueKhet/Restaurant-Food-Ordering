package com.ternion.RFO.entity;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PaymentData implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;

	public PaymentData() {
	}
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
	private SaleHeaderData saleHeaders;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int hId;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private double totalAmount;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private double paidAmount;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private double changeAmount;

//	@JsonIgnore
//	@OneToOne(mappedBy = "payment")
//	private SaleHeaderData saleHeaders;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String modifiedAt;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int gethId() {
		return hId;
	}

	public void sethId(int hId) {
		this.hId = hId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public double getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

//	@JsonBackReference
	public SaleHeaderData getSaleHeaders() {
		return saleHeaders;
	}

	public void setSaleHeaders(SaleHeaderData saleHeaders) {
		this.saleHeaders = saleHeaders;
	}
}