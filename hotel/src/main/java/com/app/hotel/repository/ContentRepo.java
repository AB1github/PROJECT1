package com.app.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.hotel.model.Contents;

public interface ContentRepo extends JpaRepository<Contents, Integer> {
	
	List<Contents> findAllBymenu_Id(int id);

}
