package com.ternion.RFO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.service.NrcService;

@RestController
@RequestMapping("/api/nrc")
public class NrcController {

	@Autowired
	NrcService nrcService;
	
	@GetMapping("/code")
	public List<String> getNrcCode() {
		return nrcService.getNrcCode();
	}
	
	@GetMapping("/all/township/{nrc_code}")
	public List<String> getNrcTownship(@PathVariable int nrc_code) {
		return nrcService.getNrcTownship(nrc_code);
	}
}