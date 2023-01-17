package com.ternion.RFO.service;

import com.ternion.RFO.entity.AccountData;

public interface AccountService {
	public AccountData checkLoginUser(String username, String password, boolean isForgetPwd);
	
	public AccountData update(int id, AccountData acc);
	
	public AccountData findById(int id);
	
	public AccountData createAcc(AccountData acc);
	
	public int getMaxId();
	
	public AccountData updatePassword(String userId, String oldPwd, String newPwd, boolean isForgetPwd);
}
