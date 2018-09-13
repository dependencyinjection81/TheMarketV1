package com.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
  
  
}