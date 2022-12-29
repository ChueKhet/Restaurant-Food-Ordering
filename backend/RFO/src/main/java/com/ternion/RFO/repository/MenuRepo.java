package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ternion.RFO.entity.MenuData;

public interface MenuRepo extends JpaRepository<MenuData, Integer> {
	
}