package com.market.repositories;

import com.market.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUsername(final String userName);
  
  UserEntity findByEmail(final String email);
  
  UserEntity findByConfirmationToken(final String confirmationToken);
 
}
