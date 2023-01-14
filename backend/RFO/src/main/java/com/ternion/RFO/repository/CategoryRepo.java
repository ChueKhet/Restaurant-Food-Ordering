package com.ternion.RFO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ternion.RFO.entity.CategoryData;

public interface CategoryRepo extends JpaRepository<CategoryData, Integer> {

}