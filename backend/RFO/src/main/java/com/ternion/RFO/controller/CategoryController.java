package com.ternion.RFO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.CategoryData;
import com.ternion.RFO.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService catService;

	@GetMapping("/all")
	public List<CategoryData> getCategoryList() {
		return catService.getCategoryList();
	}
}