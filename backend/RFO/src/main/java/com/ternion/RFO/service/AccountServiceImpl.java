package com.ternion.RFO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Override
	public UserData checkLoginUser(String username, String password) {
		UserData user = accRepo.findByUserName(username);
		
		if (user == null) {
			return null;
		}
		
		/*
		if (!pwEncoder.matches(password, user.getPassword())) {
			return null;
		}
		*/

		if (!password.equals(user.getPassword())) {
			return null;
		}
		
		return user;
	}
}
