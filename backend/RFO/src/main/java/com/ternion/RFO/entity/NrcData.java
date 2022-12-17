package com.ternion.RFO.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NrcData implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private int nrc_code;
	
	@Column(nullable = false)
	private String township_names;

	public int getNrc_code() {
		return nrc_code;
	}

	public void setNrc_code(int nrc_code) {
		this.nrc_code = nrc_code;
	}

	public String getTownship_names() {
		return township_names;
	}

	public void setTownship_names(String township_names) {
		this.township_names = township_names;
	}

	@Override
	public String toString() {
		return "NrcData [nrc_code=" + nrc_code + ", township_names=" + township_names + "]";
	}
}
