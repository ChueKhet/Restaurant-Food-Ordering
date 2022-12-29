package com.ternion.RFO.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IngredientData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy = "ingredientList")
	List<MenuData> menuDataList;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String code;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String description;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int userid;
	
	@Column(nullable = true)
	private String createdAt;
	
	@Column(nullable = true)
	private String modifiedAt;
	
	@Column(nullable = false)
	private int deleteStatus;
	
	private int status = 0;

//	@JsonIgnore
	@JsonBackReference
	public List<MenuData> getMenuDataList() {
		return menuDataList;
	}

	public void setMenuDataList(List<MenuData> menuDataList) {
		this.menuDataList = menuDataList;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}