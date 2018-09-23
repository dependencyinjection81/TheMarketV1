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
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    /*TODO These booleans aren't in use for now*/
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    
    try {
      User user = userRepository.findByEmail(email);
      if (user == null) {
        throw new UsernameNotFoundException("No user found with username: " + email);
      }
      
      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : user.getRoles()){
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }

      return new org.springframework.security.core.userdetails.User(
          user.getEmail(),
          user.getPassword(), 
          user.getEnabled(), 
          accountNonExpired,
          credentialsNonExpired, 
          accountNonLocked, 
          grantedAuthorities);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}