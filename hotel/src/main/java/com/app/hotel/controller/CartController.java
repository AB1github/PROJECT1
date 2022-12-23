package com.app.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.hotel.global.GlobalData;
import com.app.hotel.model.Contents;
import com.app.hotel.services.ContentService;

@Controller
public class CartController {

	@Autowired
	ContentService ContentService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCard(@PathVariable int id)
	{
		GlobalData.cart.add(ContentService.getContentById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cardGet(Model m)
	{
		m.addAttribute("cartCount",GlobalData.cart.size());
		m.addAttribute("total",GlobalData.cart.stream().mapToDouble(Contents::getPrice).sum());
		m.addAttribute("cart",GlobalData.cart);
		return "cart";
	}
	@GetMapping("/cart/removeItem/{index}")
	public String cardItemRemove(@PathVariable int index)
	{
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model m)
	{
		m.addAttribute("total",GlobalData.cart.stream().mapToDouble(Contents::getPrice).sum());
		return "checkout";
	}
}
