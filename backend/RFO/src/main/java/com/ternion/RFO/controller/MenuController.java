package com.ternion.RFO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ternion.RFO.entity.MenuData;
import com.ternion.RFO.service.MenuService;

@RestController
@RequestMapping("/api/menu")

public class MenuController {
	@Autowired
	MenuService menuService;
	
	@GetMapping("/ingredient/all")
	public List<MenuData> allMenuWithIngredient() {
		return menuService.allMenuWithIngredient();
	}
	
	@GetMapping("/all")
	public List<MenuData> listMenu() {
		return menuService.getAll();
	}
}