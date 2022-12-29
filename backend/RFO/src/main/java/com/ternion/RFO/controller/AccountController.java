package com.ternion.RFO.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	AccountService accService;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<AccountData> login(@Valid @RequestBody HashMap<String,Object> data) {
		AccountData user = accService.checkLoginUser(data.get("username").toString(), data.get("password").toString());
		
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/update")
	public ResponseEntity<AccountData> update(@Valid @RequestBody AccountData acc) {
		if (acc.getId() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		AccountData updatedAcc = accService.update(acc.getId(), acc );
		if (updatedAcc == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedAcc);
	}
	
}
