package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class MenuData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//	@ManyToOne
//	@JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false)
//	private Category category;
//	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int category_id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String code;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String price;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String description;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int user_id;
	
	@Column(nullable = true)
	private String created_at;
	
	@Column(nullable = true)
	private String modified_at;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "MenuData [id=" + id + ", category_id=" + category_id + ", code=" + code + ", price=" + price
				+ ", description=" + description + ", user_id=" + user_id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + "]";
	}

	
}