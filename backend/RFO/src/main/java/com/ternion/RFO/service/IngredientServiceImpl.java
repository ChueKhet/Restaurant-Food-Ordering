package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.IngredientData;
import com.ternion.RFO.repository.IngredientRepo;

public class IngredientServiceImpl implements IngredientService {
	@Autowired
	IngredientRepo ingredientRepo;

	@Override
	public List<IngredientData> getAll() {
		return ingredientRepo.findAll();
	}

	@Override
	public IngredientData create(IngredientData ingredient) {
		return ingredientRepo.save(ingredient);
	}

	@Override
	public IngredientData update(IngredientData ingredient) {
		IngredientData findIngredient = ingredientRepo.findById(ingredient.getId()).orElse(null);
		if (findIngredient == null) {
			return null;
		}
	
		findIngredient.setMenu(ingredient.getMenu());
	//	findIngredient.setPrice(ingredient.getPrice());
		findIngredient.setUserId(ingredient.getUserId());
		findIngredient.setModifiedDate(ingredient.getModifiedDate());
		
		return ingredientRepo.save(findIngredient);
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
