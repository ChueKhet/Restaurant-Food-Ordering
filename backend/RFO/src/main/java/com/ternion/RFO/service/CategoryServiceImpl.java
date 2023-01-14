package com.ternion.RFO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternion.RFO.entity.CategoryData;
import com.ternion.RFO.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo catRepo;

	@Override
	public List<CategoryData> getCategoryList() {
		return catRepo.findAll();
	}
}