package com.ternion.RFO.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.UserData;
import com.ternion.RFO.repository.UserRepo;
import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.repository.AccountRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AccountRepo accRepo;

	@Autowired
	PasswordEncoder pwEncoder;

	public UserData createUser(UserData user)  {
		return userRepo.save(user);
	}

	@Override
	public ArrayList<HashMap<String, Object>> getAll() {
		ArrayList<HashMap<String, Object>> userAccList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> userAccData = new HashMap<String, Object>();
		
		UserData userData = new UserData();
		List<UserData> userList = userRepo.findAllByStaff();
		
		AccountData accData = new AccountData();
		
		for(int i = 0; i < userList.size(); i++) {
			accData = new AccountData();
			userAccData = new HashMap<String, Object>();
			
			accData = accRepo.findByParentId(userList.get(i).getId());
			
			userAccData.put("user", userList.get(i));
			userAccData.put("acc", accData);
			
			userAccList.add(userAccData);
		}
		
		return userAccList;
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
		userdata.setGender(user.getGender());
		userdata.setPhone(user.getPhone());
		userdata.setAddress(user.getAddress()); 
		userdata.setModifiedAt(user.getModifiedAt());
		
		return userRepo.save(userdata);
	}
	
	@Override
	public UserData findById(int id) {
		return userRepo.findById(id).orElse(null); 

	}

}
