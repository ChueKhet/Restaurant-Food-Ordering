package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class AccountData implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private int parentId;
	
	@Column(length = 30,nullable = false)
	@NotBlank(message = "Required")
	private String userid;
	
	@Column(length = 30,nullable = false)
	@NotBlank(message = "Required")
	private String username;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "Required")
	private String password;	
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String createdAt;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String modifiedAt;
	
	@Column(nullable = false)
	@NotNull(message = "Required")
	private int role;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	
}