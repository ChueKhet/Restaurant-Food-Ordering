package com.ternion.RFO.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class MenuData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	@JoinTable(
		name = "menu_ingredient",
		joinColumns = @JoinColumn(name = "menu_id"),
		inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	)
	private List<IngredientData> ingredientList;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private int category_id;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String code;
	
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
	
	@Column(nullable = false)
	private int deleteStatus;
	
	@Column(nullable = false)
	private double price;

	public List<IngredientData> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<IngredientData> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuData [ingredientList=" + ingredientList + ", id=" + id + ", category_id=" + category_id + ", code="
				+ code + ", description=" + description + ", user_id=" + user_id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", deleteStatus=" + deleteStatus + ", price=" + price + "]";
	}
}