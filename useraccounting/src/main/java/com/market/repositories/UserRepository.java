package com.market.repositories;

import com.market.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(final String userName);
  
  User findByEmail(final String email);
 
}
