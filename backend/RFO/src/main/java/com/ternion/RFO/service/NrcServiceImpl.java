package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.NrcData;
import com.ternion.RFO.repository.NrcRepo;

@Service
public class NrcServiceImpl implements NrcService{
	@Autowired
	NrcRepo nrcRepo;

	@Override
	public List<String> getNrcCode() {
		return nrcRepo.findGroupByNrcCode();
//		return null;
	}

	@Override
	public List<String> getNrcTownship(int nrc_code) {	
		return nrcRepo.findByNrcCode(nrc_code);
//		return null;
	}
	

}
