package com.app.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.hotel.model.Contents;
import com.app.hotel.repository.ContentRepo;

@Service
public class ContentService {

	@Autowired
	private ContentRepo contentRepo;
	public List<Contents> getAllContents()
        {
		   return contentRepo.findAll();
		}
	public void addContent(Contents content)
	{
		contentRepo.save(content);
	}
	public void removeContentById(int id)
	{
		contentRepo.deleteById(id);
	}
	
	public Optional<Contents> getContentById(int id)
	{
		return contentRepo.findById(id);
	}
	
	public List<Contents> getAllContentByMenuId(int id)
	{
		return contentRepo.findAllBymenu_Id(id);
	}
}
