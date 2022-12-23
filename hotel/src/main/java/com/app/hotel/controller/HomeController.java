package com.app.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.hotel.global.GlobalData;
import com.app.hotel.services.ContentService;
import com.app.hotel.services.menuService;

@Controller
public class HomeController {
	@Autowired
	ContentService contentService;
	@Autowired
	menuService menuService;
	
	@GetMapping({"/","/home"})
	public String home(Model m)
	{
		return "index";
	}
	
	@GetMapping("/shop")
	public String Shop(Model m)
	{
		m.addAttribute("menu", menuService.getAllMenu());
		m.addAttribute("content", contentService.getAllContents());
		m.addAttribute("cardCount",GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/menu/{id}")
	public String ShopByMenu(Model m, @PathVariable int id)
	{
		m.addAttribute("menu", menuService.getAllMenu());
		m.addAttribute("content", contentService.getAllContentByMenuId(id));
		m.addAttribute("cardCount",GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/view/{id}")
	public String viewContent(Model m, @PathVariable int id)
	{
		m.addAttribute("content", contentService.getContentById(id).get());
		m.addAttribute("cardCount",GlobalData.cart.size());
		return "view";
	}
	
	
	
	

}
