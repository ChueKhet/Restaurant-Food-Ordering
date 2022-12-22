package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.MenuData;
import com.ternion.RFO.repository.MenuRepo;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepo menuRepo;

	@Override
	public List<MenuData> getAll() {	
		return menuRepo.findAll();
	}
	

}
