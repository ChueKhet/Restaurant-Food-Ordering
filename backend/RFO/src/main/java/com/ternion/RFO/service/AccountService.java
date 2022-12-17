package com.ternion.RFO.service;

import com.ternion.RFO.entity.UserData;

public interface AccountService {
	public UserData checkLoginUser(String username, String password);
}
