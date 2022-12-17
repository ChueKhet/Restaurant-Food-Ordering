package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class UserData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 30,nullable = false)
	@NotBlank(message = "Required")
	private String name;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String dob;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String nrc;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String phone;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String address;
	
	@Column(length = 30,nullable = false)
	@NotBlank(message = "Required")
	private String username;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message = "Required")
	private String password;
	
//	@Column(length = 100, nullable = false)
//	@NotBlank(message = "Required")
//	private String confirmpwd;

	@Column(nullable = true)
	private String createdDate;

	@Column(nullable = true)
	private String modifiedDate;

	@Column(nullable = true)
	private String createdTime;
	
	@Column(nullable = true)
	private String modifiedTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	

}
