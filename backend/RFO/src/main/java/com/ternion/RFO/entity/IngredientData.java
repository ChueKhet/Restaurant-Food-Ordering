package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class IngredientData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String code;
	
	@OneToOne
	@JoinColumn(name = "menuId", referencedColumnName = "id", nullable = false)
	private MenuData menu;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int category_id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int userId;
	
	@Column(nullable = true)
	private String createdDate;
	
	@Column(nullable = true)
	private String modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MenuData getMenu() {
		return menu;
	}

	public void setMenu(MenuData menu) {
		this.menu = menu;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "IngredientData [id=" + id + ", code=" + code + ", menu=" + menu + ", category_id=" + category_id
				+ ", userId=" + userId + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	

	
}