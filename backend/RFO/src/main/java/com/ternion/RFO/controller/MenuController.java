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
	
	@GetMapping("/list")
	public List<MenuData> listMenu() {
		return menuService.getAll();
		
	}
	
	@PostMapping("/add")
	MenuData create(@RequestBody MenuData menu) {
		return menuService.create(menu);
	}
	
	@PutMapping("/update")
	ResponseEntity<?> update(@RequestBody MenuData menu) {
		MenuData updatedMenu = menuService.update(menu);
		if (updatedMenu == null) {
			return ResponseEntity.badRequest().body("Menu cannot be updated");
		}
		//receipt.setDate(LocalDateTime.now());
		return ResponseEntity.ok(updatedMenu);
	}

	@DeleteMapping("/del")
	ResponseEntity<?> delete(@RequestBody MenuData menu) {
		 boolean isDeleted = menuService.delete(menu.getId());
		 if (isDeleted == false) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok(isDeleted);
		 
	}
	
	

}
