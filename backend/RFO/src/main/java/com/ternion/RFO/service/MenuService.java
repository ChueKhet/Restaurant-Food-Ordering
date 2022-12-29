package com.ternion.RFO.service;

import java.util.List;

import com.ternion.RFO.entity.MenuData;

public interface MenuService {

	public List<MenuData> allMenuWithIngredient();

	public List<MenuData> getAll();

	public MenuData create(MenuData menu);

	public MenuData update(MenuData menu);

	public boolean delete(int id);
}