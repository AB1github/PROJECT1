package com.app.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.hotel.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
