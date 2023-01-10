package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder pwEncoder;

	public UserData createUser(UserData user)  {
		return userRepo.save(user);
	}

	@Override
	public List<UserData> getAll() {	
		return userRepo.findAll();
	}

	@Override
	public boolean delete(int id) {
		
		UserData userdata = userRepo.findById(id).orElse(null);
		if(userdata == null) {
			return false;
		}
		
		userRepo.deleteById(id);
		return true;
	}

	@Override
	public UserData update(int id, UserData user) {
		UserData userdata = this.findById(id);
		if(userdata == null) {
			return null;
		}
		userdata.setName(user.getName()); 
		userdata.setDob(user.getDob());
		userdata.setNrc(user.getNrc()); 
		userdata.setPhone(user.getPhone());
		userdata.setAddress(user.getAddress()); 
		userdata.setModifiedAt(user.getModifiedAt());
		userRepo.save(userdata);
		
		return null;
	}
	
	@Override
	public UserData findById(int id) {
		return userRepo.findById(id).orElse(null); 

	}

}
