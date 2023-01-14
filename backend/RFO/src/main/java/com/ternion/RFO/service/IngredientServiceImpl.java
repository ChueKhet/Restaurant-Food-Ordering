package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.IngredientData;
import com.ternion.RFO.repository.IngredientRepo;
import com.ternion.RFO.utility.ServerUtil;

@Service
public class IngredientServiceImpl implements IngredientService {
	@Autowired
	IngredientRepo ingredientRepo;

	@Override
	public List<IngredientData> getAll() {
		return ingredientRepo.findAll();
	}
	
	@Override
	public IngredientData create(IngredientData ingredient) {
		String curDate = ServerUtil.getCurrentDate();
		ingredient.setCreatedAt(curDate);
		ingredient.setModifiedAt(curDate);
		
		return ingredientRepo.save(ingredient);
	}

	@Override
	public IngredientData update(IngredientData ingredient) {
		String curDate = ServerUtil.getCurrentDate();
		
		ingredientRepo.deleteById(ingredient.getId());
		ingredient.setId(0);
		ingredient.setModifiedAt(curDate);
		
		return ingredientRepo.save(ingredient);
	}

	@Override
	public boolean delete(int id) {
		IngredientData findIngredient = ingredientRepo.findById(id).orElse(null);
		
		if (findIngredient == null) {
			return false;
		}
		
		ingredientRepo.deleteById(id);
		
		return true;
	}
}