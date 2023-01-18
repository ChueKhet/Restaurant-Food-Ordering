package com.ternion.RFO.service;

import java.util.List;

import com.ternion.RFO.entity.IngredientData;

public interface IngredientService {
	public List<IngredientData>getAll();

	public IngredientData create(IngredientData ingredient);

	public IngredientData update(IngredientData ingredient);

	public boolean delete(int id);
	
	public IngredientData findByCode(String code);
}