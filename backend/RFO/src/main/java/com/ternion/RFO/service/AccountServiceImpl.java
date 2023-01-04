package com.ternion.RFO.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.repository.AccountRepo;
import com.ternion.RFO.repository.MenuRepo;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Autowired
	UserService userservice;
	
	@Override
	public AccountData checkLoginUser(String username, String password) {
		AccountData acc = accRepo.findByUsername(username);
		
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
	
	@Override
	public AccountData update(int id, AccountData acc) {
		AccountData toUpdateAcc = this.findById(id);
		if (toUpdateAcc == null) {
			return null;
		}
		toUpdateAcc.setUsername(acc.getUsername());
		toUpdateAcc.setPassword(acc.getPassword());
		toUpdateAcc.setModifiedAt(LocalDateTime.now());
		return accRepo.save(toUpdateAcc);
	}

	@Override
	public AccountData findById(int id) {
		return accRepo.findById(id).orElse(null); 

	}

}
