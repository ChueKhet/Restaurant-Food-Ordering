package com.ternion.RFO.service;

import com.ternion.RFO.entity.AccountData;

public interface AccountService {
	public AccountData checkLoginUser(String username, String password);
	
}
