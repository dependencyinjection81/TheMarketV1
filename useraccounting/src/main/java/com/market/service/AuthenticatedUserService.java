package com.market.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.market.entities.Role;
import com.market.entities.User;
import com.market.repositories.UserRepository;

@Service
public class AuthenticatedUserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = userRepository.findByEmail(email);

      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : user.getRoles()){
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
  }
    
}
