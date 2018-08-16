package com.market.repositories;

import com.market.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);

  UserEntity findByConfirmationToken(String confirmationToken);
}
