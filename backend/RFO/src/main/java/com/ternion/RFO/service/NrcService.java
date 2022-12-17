package com.ternion.RFO.service;

import java.util.List;

import com.ternion.RFO.entity.NrcData;

public interface NrcService {
	public List<String> getNrcCode();
	public List<String> getNrcTownship(int nrc_code);
}
