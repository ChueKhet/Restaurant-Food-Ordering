package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class PaymentData implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int headerId;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int totalAmout;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int paidAmount;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int changeAmount;
	
	@Column(nullable = true)
	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = true)
	@NotBlank(message = "Required")
	private String modifiedAt;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHeaderId() {
		return headerId;
	}

	public void setHeaderId(int headerId) {
		this.headerId = headerId;
	}

	public int getTotalAmout() {
		return totalAmout;
	}

	public void setTotalAmout(int totalAmout) {
		this.totalAmout = totalAmout;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public int getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(int changeAmount) {
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

}
