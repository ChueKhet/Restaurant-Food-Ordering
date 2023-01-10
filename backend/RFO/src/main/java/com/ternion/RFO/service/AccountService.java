package com.ternion.RFO.service;

import com.ternion.RFO.entity.AccountData;
import com.ternion.RFO.entity.UserData;

public interface AccountService {
	public AccountData checkLoginUser(String username, String password, boolean isForgetPwd);
	
	public AccountData update(int id, AccountData acc);
	
	public AccountData findById(int id);
	
	public AccountData createAcc(AccountData acc);
	
	public int getMaxId();
	
	public UserData updatePassword(String userId, String oldPwd, String newPwd, boolean isForgetPwd);
}
