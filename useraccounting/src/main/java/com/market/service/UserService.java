package com.market.service;

import com.market.entities.UserEntity;
import com.market.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity findByEmail(final String email) {
    return userRepository.findByEmail(email);
  }

  public UserEntity findByConfirmationToken(final String confirmationToken) {
    return userRepository.findByConfirmationToken(confirmationToken);
  }

  public void saveUser(final UserEntity user) {
    userRepository.save(user);
  }

}
