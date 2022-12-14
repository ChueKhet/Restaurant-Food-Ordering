package com.ternion.RFO.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ternion.RFO.entity.IngredientData;

public interface IngredientRepo extends JpaRepository<IngredientData, Integer> {
	@Query(value = "select i.* from ingredient_data i "
			+ "inner join menu_ingredient mi on mi.ingredient_id = i.id "
			+ "where menu_id = ?", nativeQuery = true)
	public List<IngredientData> getAllByMenuId(int id);
}


