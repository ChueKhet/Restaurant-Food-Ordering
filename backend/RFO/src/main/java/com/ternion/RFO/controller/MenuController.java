package com.ternion.RFO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<MenuData> retList = menuService.getAll();
		
		for(int i = 0; i < retList.size(); i++) {
			retList.get(i).setCatId(retList.get(i).getCategory().getId());
		}
		
		return retList;
	}

	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody MenuData data) {
		MenuData menuData = menuService.create(data);
		
		if (menuData == null) {
			return ResponseEntity.badRequest().body("Menu already exists!");
		}
		
		return ResponseEntity.ok().body(menuData);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody MenuData menu) {
		MenuData updatedMenu = menuService.update(menu);
		if (updatedMenu == null) {
			return ResponseEntity.badRequest().body("Menu cannot be updated");
		}
		//receipt.setDate(LocalDateTime.now());
		return ResponseEntity.ok(updatedMenu);
	}

	@DeleteMapping("/del")
	public ResponseEntity<?> delete(@RequestBody MenuData menu) {
		 boolean isDeleted = menuService.delete(menu.getId());
		 if (isDeleted == false) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok(isDeleted);
		 
	}
}