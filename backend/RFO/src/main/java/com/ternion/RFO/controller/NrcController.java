package com.ternion.RFO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.NrcData;
import com.ternion.RFO.service.NrcService;

@RestController
@RequestMapping("/nrc")
public class NrcController {

	@Autowired
	NrcService nrcService;
	
	@GetMapping("/getNrcCode")
	public List<String> getNrcCode() {
		return nrcService.getNrcCode();
	
	}
	
	@GetMapping("/getNrcTownship/{nrcCode}")
	public List<String> getNrcTownship(@PathVariable int nrcCode) {
		return nrcService.getNrcTownship(nrcCode);
	
	}
	
}
