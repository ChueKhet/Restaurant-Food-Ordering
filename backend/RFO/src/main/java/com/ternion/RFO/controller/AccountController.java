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
		
		UserData user = new UserData();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("message", "SUCCESS");
		
		if (acc == null) {
			result.put("message", "ID_NOT_FOUND");
			
			return ResponseEntity.ok().body(result);
		} else {
			if(acc.getPassword().equals("")) {
				result.put("message", "WRONG_PASSWORD");
				
				return ResponseEntity.ok().body(result);
			}
			
			user = userService.findById(acc.getParentId());
			
			if(user == null && (!acc.getUserid().equals("admin"))) {
				result.put("message", "USER_NOT_FOUND");
				
				return ResponseEntity.ok().body(result);
			}
		}
		
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("message", "SUCCESS");
		
		AccountData acc = accService.updatePassword(data.get("userid").toString(), data.get("oldPwd").toString(), 
				data.get("newPwd").toString(), isForgetPwd);
		
		if(acc == null) {
			result.put("message", "ID_NOT_FOUND");
		} else if(acc.getPassword().equals("")) {
			result.put("message", "WRONG_PASSWORD");
		} else {
			result.put("acc", acc);
		}
		
		return ResponseEntity.ok().body(result);
	}
}