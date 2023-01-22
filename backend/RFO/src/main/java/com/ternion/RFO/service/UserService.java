package com.ternion.RFO.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ternion.RFO.entity.UserData;

public interface UserService {
	
	public UserData createUser(UserData user);
	
	public ArrayList<HashMap<String, Object>> getAll();
	
	public boolean delete(int id);
	
	public UserData update(int id, UserData user);
	
	public UserData findById(int id);
} 