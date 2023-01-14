package com.ternion.RFO.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CategoryData implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
	private String userid;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String modifiedAt;	
	
	@OneToMany(mappedBy = "category")
	List<MenuData> menuList;

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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
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

	//	@JsonBackReference
	@JsonManagedReference
	public List<MenuData> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuData> menuList) {
		this.menuList = menuList;
	}
}