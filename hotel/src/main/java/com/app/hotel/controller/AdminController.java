package com.app.hotel.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.hotel.dto.ContentsDTO;
import com.app.hotel.model.Contents;
import com.app.hotel.model.menu;
import com.app.hotel.services.ContentService;
import com.app.hotel.services.menuService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/contentImages";

	@Autowired
	private menuService menuService;
	@Autowired
	private ContentService contentService;
	
	
	@GetMapping("/admin")
	public String getAdmin()
	{
		return "admin";
	}
	
	@GetMapping("/admin/menu")
	public String getMenu(Model m)
	{
		m.addAttribute("menu",menuService.getAllMenu());
		return "menu";
	}

	@GetMapping("/admin/menu/add")
	public String getMenuAdd(Model m)
	{
		m.addAttribute("menu",new menu());
		return "menuAdd";
	}
	
	@PostMapping("/admin/menu/add")
	public String postMenuAdd(@ModelAttribute("menu") menu menu)
	{
		menuService.addMenu(menu);
		return "redirect:/admin/menu";
	}
	
	@GetMapping("/admin/menu/delete/{id}")
	public String deleteMenu(@PathVariable int id)
	{
		menuService.deleteMenuById(id);
		return "redirect:/admin/menu";
	}
	
	@GetMapping("/admin/menu/update/{id}")
	public String updateMenu(@PathVariable int id, Model m)
	{
		Optional<menu> menu=menuService.getMenuById(id);
		if(menu.isPresent())
		{
			m.addAttribute("menu", menu.get());
			return "menuAdd";
		}
		else
			return "404";
	}
	
	//Contents Section
	
	@GetMapping("/admin/content")
	public String getContent(Model m)
	{
		m.addAttribute("content",contentService.getAllContents());
		return "content";
	}
	
	@GetMapping("/admin/content/add")
	public String productAdd(Model m)
	{
		m.addAttribute("contentDTO",new ContentsDTO());
		m.addAttribute("menu",menuService.getAllMenu());
		return "contentAdd";
	}
	
	@PostMapping("/admin/content/add")
	public String contentAddPost(@ModelAttribute("contentDTO")ContentsDTO contentDTO,
			                      @RequestParam("contentImages")MultipartFile file,
			                      @RequestParam("imageName")String imageName) throws IOException
	{
		Contents content=new Contents();
		content.setId(contentDTO.getId());
		content.setName(contentDTO.getName());
		content.setMenu(menuService.getMenuById(contentDTO.getMenuId()).get());
		content.setPrice(contentDTO.getPrice());
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}
		else
		{
			imageUUID=imageName;
		}
		content.setImageName(imageUUID);
		contentService.addContent(content);
		return "redirect:/admin/content";
	}
	
	@GetMapping("/admin/content/delete/{id}")
	public String deleteContent(@PathVariable int id)
	{
		contentService.removeContentById(id);
		return "redirect:/admin/content";
	}
	
	@GetMapping("/admin/content/update/{id}")
	public String updateContent(@PathVariable int id, Model m)
	{
		Contents content=contentService.getContentById(id).get();
		ContentsDTO contentDTO=new ContentsDTO();
		contentDTO.setId(content.getId());
		contentDTO.setName(content.getName());
		contentDTO.setMenuId(content.getMenu().getId());
		contentDTO.setPrice(content.getPrice());
		contentDTO.setImageName(content.getImageName());
		m.addAttribute("menu", menuService.getAllMenu());
		m.addAttribute("contentDTO", contentDTO);
		return "contentAdd";
	}
}
