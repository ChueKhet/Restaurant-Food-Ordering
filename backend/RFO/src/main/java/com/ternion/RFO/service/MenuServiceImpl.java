package com.ternion.RFO.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.IngredientData;
import com.ternion.RFO.entity.MenuData;
import com.ternion.RFO.repository.IngredientRepo;
import com.ternion.RFO.repository.MenuRepo;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepo menuRepo;

	@Autowired
	IngredientRepo ingredientRepo;

	@Override
	public List<MenuData> allMenuWithIngredient() {	
		 List<MenuData> data= menuRepo.findAll();
		 List<IngredientData> ingredientList = null;
		 
		 for(int i=0; i<data.size(); i++) {
			 ingredientList = new ArrayList<>();
			 ingredientList = ingredientRepo.getAllByMenuId(data.get(i).getId());
			 
			 data.get(i).setIngredientList(ingredientList);
		 }
		 
		 return data;
	}

	@Override
	public List<MenuData> getAll() {	
		return menuRepo.findAll();
	}

	@Override
	public MenuData create(MenuData menu) {
		return menuRepo.save(menu);
	}

	@Override
	public MenuData update(MenuData menu) {
		MenuData findMenu = menuRepo.findById(menu.getId()).orElse(null);
		
		if (findMenu == null) {
			return null;
		}

		findMenu.setPrice(menu.getPrice());
		findMenu.setDescription(menu.getDescription());
		findMenu.setModified_at(menu.getModified_at());
		findMenu.setUser_id(menu.getUser_id());
		
		return menuRepo.save(findMenu);
	}

	@Override
	public boolean delete(int id) {
		MenuData findMenu = menuRepo.findById(id).orElse(null);
		
		if (findMenu == null) {
			return false;
		}
		
		menuRepo.deleteById(id);
		return true;
	}
}