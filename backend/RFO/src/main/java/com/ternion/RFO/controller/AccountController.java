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
import com.ternion.RFO.service.UserService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	AccountService accService;

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody HashMap<String,Object> data) {
		AccountData acc = accService.checkLoginUser(data.get("userid").toString(), data.get("password").toString(), false);
		
		UserData user = userService.findById(acc.getParentId());
		
		if (acc == null || user == null) {
			return ResponseEntity.badRequest().build();
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("account", acc);
		result.put("user", user);
		
		return ResponseEntity.ok().body(result);
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
	
	@PutMapping("/password/update")
	public ResponseEntity<?> updatePassword(@Valid @RequestBody HashMap<String, Object> data) {
		boolean isForgetPwd = data.get("isForgetPwd").toString().equals("true")? true : false;
		
		accService.updatePassword(data.get("userId").toString(),data.get("oldPwd").toString(), 
				data.get("newPwd").toString(), isForgetPwd);
		
		return ResponseEntity.ok().body("Password Changed!!!");
	}
	
}
