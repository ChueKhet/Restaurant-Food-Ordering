package com.ternion.RFO.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.service.UserService;

@RestController
@RequestMapping("/api/register")
public class UserController {
	@Autowired
	UserService userService;

//	@Autowired
//	PasswordEncoder pwEncoder;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> register(@Valid @RequestBody UserData user) {
		UserData userdata = userService.createUser(user);
		if (user == null) {
			return ResponseEntity.badRequest()
					.body("User already exists!");
		}
		return ResponseEntity.ok().body(userdata);
	}
	
	@GetMapping("/list")
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


}
