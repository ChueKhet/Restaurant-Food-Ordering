package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


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

	@Column(nullable = true)
	private String createdAt;

	@Column(nullable = true)
	private String modifiedAt;
	
	@Column(length = 200)
	@NotBlank(message = "Required")
	private String imagePath;
	
	@Column(nullable = false)
	@NotBlank(message = "Required")
	private String gender;
	
	
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + ", name=" + name + ", dob=" + dob + ", nrc=" + nrc + ", phone=" + phone
				+ ", address=" + address + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
}