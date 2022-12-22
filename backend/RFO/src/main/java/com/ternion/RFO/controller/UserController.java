package com.ternion.RFO.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.service.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	RegisterService regService;

//	@Autowired
//	PasswordEncoder pwEncoder;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> register(@Valid @RequestBody UserData user) {
		UserData userdata = regService.createUser(user);
		if (user == null) {
			return ResponseEntity.badRequest()
					.body("User already exists!");
		}
		return ResponseEntity.ok().body(userdata);
	}

}
