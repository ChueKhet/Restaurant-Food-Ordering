package com.ternion.RFO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ternion.RFO.entity.MenuData;

public interface MenuRepo extends JpaRepository<MenuData, Integer> {
	
	@Query(value = "Select * from menu_data where category_id in (:categories)", nativeQuery = true)
	public List<MenuData> getMenuByCategory(@Param("categories") List<String> categories);		//		String categories
	
}