package com.ternion.RFO.controller;

import java.util.HashMap;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.service.AccountService;
import com.ternion.RFO.service.UserService;
import com.ternion.RFO.utility.ServerUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	AccountService accService;

	@SuppressWarnings("unchecked")
	@PostMapping("/add")
	public ResponseEntity<?> register(@Valid @RequestBody HashMap<String, Object> data) {
		HashMap<String, Object> retData = new HashMap<String, Object>();
		
		UserData user = new UserData();
		AccountData acc = new AccountData();
		
		String curDate = ServerUtil.getCurrentDate();
		
		user.setName(((HashMap<String, Object>) data.get("userData")).get("name").toString());
		user.setDob(((HashMap<String, Object>) data.get("userData")).get("dob").toString());
		user.setNrc(((HashMap<String, Object>) data.get("userData")).get("nrc").toString());
		user.setPhone(((HashMap<String, Object>) data.get("userData")).get("phone").toString());
		user.setAddress(((HashMap<String, Object>) data.get("userData")).get("address").toString());
		user.setImagePath(((HashMap<String, Object>) data.get("userData")).get("imagePath").toString());
		user.setGender(((HashMap<String, Object>) data.get("userData")).get("gender").toString());
		user.setCreatedAt(curDate);
		user.setModifiedAt(curDate);
		
		int maxId = accService.getMaxId();
		
		acc.setUserid(ServerUtil.generateUserId(maxId));
		acc.setUsername(((HashMap<String, Object>) data.get("accountData")).get("username").toString());
		acc.setPassword(((HashMap<String, Object>) data.get("accountData")).get("password").toString());
		acc.setRole(0);
		acc.setCreatedAt(curDate);
		acc.setModifiedAt(curDate);
		
		UserData userdata = userService.createUser(user);
		
		if (userdata == null) {
			return ResponseEntity.badRequest().body("User already exists!");
		} else {
			retData.put("userData", userdata);
			acc.setParentId(userdata.getId());
			
			AccountData accdata = accService.createAcc(acc);
			
			if (accdata == null) {
				return ResponseEntity.badRequest().body("Account already exists!");
			} else {
				retData.put("accData", accdata);
			}
		}
		
		return ResponseEntity.ok().body(retData);
	}
	
	@GetMapping("/all")
	public List<UserData> listUser() {
		return userService.getAll();
	}
	
	@DeleteMapping("/delete")
	ResponseEntity<?> delete(@RequestBody UserData userdata){
		boolean isDeleted = userService.delete(userdata.getId());
		
		if(isDeleted == false) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserData> updateProfile(@Valid @RequestBody UserData user) {
		String curDate = ServerUtil.getCurrentDate();
		
		if (user.getId() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		
		user.setModifiedAt(curDate);
		
		UserData updatedUser = userService.update(user.getId(), user);
		
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(updatedUser);
	}
	
}