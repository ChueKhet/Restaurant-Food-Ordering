package com.ternion.RFO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.repository.RegisterRepo;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterRepo regRepo;

	@Autowired
	PasswordEncoder pwEncoder;

	public UserData createUser(UserData user)  {
		return regRepo.save(user);
	}

	
}
