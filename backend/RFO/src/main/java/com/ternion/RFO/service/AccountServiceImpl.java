package com.ternion.RFO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Override
	public AccountData checkLoginUser(String username, String password) {
		AccountData acc = accRepo.findByUserName(username);
		
		if (acc == null) {
			return null;
		}
		
		/*
		if (!pwEncoder.matches(password, user.getPassword())) {
			return null;
		}
		*/

		if (!password.equals(acc.getPassword())) {
			return null;
		}
		
		return acc;
	}

}
