package com.ternion.RFO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ternion.RFO.entity.MenuData;

public interface MenuRepo extends JpaRepository<MenuData, Integer> {
	
	@Query(value = "Select * from menu_data where category_id in (:categories)", nativeQuery = true)
	public List<MenuData> getMenuByCategory(@Param("categories") List<String> categories);		//		String categories
	
	public MenuData findByCode(String code);
	
	@Modifying
	@Query(value = "UPDATE menu_data SET modified_at=?, image_path=?, category_id=?, price=?, description=?, userid=? WHERE id=?", nativeQuery = true)
	public void updateMenu(String modifiedAt, String imagePath, int categoryId, double price, String description, String userid, int id);
}