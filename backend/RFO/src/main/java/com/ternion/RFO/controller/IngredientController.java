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

import com.ternion.RFO.entity.IngredientData;
import com.ternion.RFO.service.IngredientService;

@RestController
@RequestMapping("/api/ingredient/")
public class IngredientController {
	@Autowired
	IngredientService ingredientService;
	
	@PostMapping("/add")
	public ResponseEntity<?> create(@RequestBody IngredientData data) {
		IngredientData ingreData = ingredientService.create(data);
		
		if (ingreData == null) {
			return ResponseEntity.badRequest().body("Menu already exists!");
		}
		
		return ResponseEntity.ok().body(ingreData);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody IngredientData data) {
		IngredientData updatedIng = ingredientService.update(data);
		
		if (updatedIng == null) {
			return ResponseEntity.badRequest().body("Ingredient cannot be updated");
		}
		
		return ResponseEntity.ok(updatedIng);
	}

	@DeleteMapping("/del")
	public ResponseEntity<?> delete(@RequestBody IngredientData data) {
		 boolean isDeleted = ingredientService.delete(data.getId());
		 
		 if (isDeleted == false) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 return ResponseEntity.ok(isDeleted);	 
	}

	@GetMapping("/all")
	public List<IngredientData> listMenu() {
		List<IngredientData> ingredientList = ingredientService.getAll();

		return ingredientList;
	}
}
