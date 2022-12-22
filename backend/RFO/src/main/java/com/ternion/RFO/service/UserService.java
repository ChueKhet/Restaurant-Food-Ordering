package com.ternion.RFO.service;

import java.util.List;

import com.ternion.RFO.entity.UserData;

public interface UserService {
	public UserData createUser(UserData user);
	public List<UserData> getAll();
	public boolean delete(int id);
} 