package com.ternion.RFO.service;

import com.ternion.RFO.entity.AccountData;

public interface AccountService {
	public AccountData checkLoginUser(String username, String password);
	
	public AccountData update(int id, AccountData acc);
	
	public AccountData findById(int id);
	
}
