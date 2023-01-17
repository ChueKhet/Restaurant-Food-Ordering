package com.ternion.RFO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.repository.AccountRepo;
import com.ternion.RFO.utility.ServerUtil;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Autowired
	UserService userservice;
	
	@Override
	public AccountData checkLoginUser(String userid, String password, boolean isForgetPwd) {
		AccountData acc = accRepo.findByUserid(userid);
		
		if (acc == null) {
			return null;
		}
		
		/*
		if (!pwEncoder.matches(password, user.getPassword())) {
			return null;
		}
		*/

		if ((!isForgetPwd) && (!password.equals(acc.getPassword())) ) {
			//		(login, change password) => isForgetPwd = false / forget password => isForgetPwd = true
			
			acc.setPassword("");
		}
		
		return acc;
	}
	
	@Override
	public AccountData update(int id, AccountData acc) {
		String curDate = ServerUtil.getCurrentDate();
		
		AccountData toUpdateAcc = this.findById(id);
		
		if (toUpdateAcc == null) {
			return null;
		}
		
		toUpdateAcc.setUsername(acc.getUsername());
		toUpdateAcc.setPassword(acc.getPassword());
		toUpdateAcc.setModifiedAt(curDate);
		
		return accRepo.save(toUpdateAcc);
	}

	@Override
	public AccountData findById(int id) {
		return accRepo.findById(id).orElse(null); 

	}

	@Override
	public AccountData createAcc(AccountData acc) {
		return accRepo.save(acc);
	}

	@Override
	public int getMaxId() {
		return accRepo.getMaxId();
	}

	@Override
	public AccountData updatePassword(String userId, String oldPwd, String newPwd, boolean isForgetPwd) {
		AccountData acc = checkLoginUser(userId, oldPwd, isForgetPwd);
		
		if(acc == null) {
			return null;
		}
		
		if(!acc.getPassword().equals("")) {
			acc.setPassword(newPwd);
			acc.setModifiedAt(ServerUtil.getCurrentDate());
			
			accRepo.save(acc);
		}
		
		return acc;
	}
}