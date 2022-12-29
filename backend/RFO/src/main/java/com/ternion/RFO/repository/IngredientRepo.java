package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ternion.RFO.entity.IngredientData;

public interface IngredientRepo extends JpaRepository<IngredientData, Integer>{

}
