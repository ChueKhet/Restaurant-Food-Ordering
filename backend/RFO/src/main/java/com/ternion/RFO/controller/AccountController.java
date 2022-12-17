package com.ternion.RFO.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accService;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<UserData> login(@Valid @RequestBody HashMap<String,Object> data) {
		UserData user = accService.checkLoginUser(data.get("username").toString(), data.get("password").toString());
		
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
}
