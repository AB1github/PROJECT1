package com.app.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.hotel.model.menu;
import com.app.hotel.repository.menuRepo;

@Service
public class menuService {
	@Autowired
	menuRepo menuRepo;
	
	public void addMenu(menu menu)
	{
		menuRepo.save(menu);
	}
	
	public List<menu> getAllMenu()
	{
		return menuRepo.findAll();
	}
	
	public void deleteMenuById(int id)
	{
		menuRepo.deleteById(id);
	}
	
	public Optional<menu> getMenuById(int id)
	{
		return menuRepo.findById(id);
	}

}
