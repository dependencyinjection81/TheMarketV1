package com.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.market.entities.UserEntity;
import com.market.repositories.UserRepository;

@Service
public class AuthenticatedUserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUserName(username);
    if (user == null) {
      throw new UsernameNotFoundException("The user " + username + " does not exist");
    }
    return new AuthenticatedUser(user);
    
  }

}
