package com.market.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser extends UserEntity implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public AuthenticatedUser(UserEntity user) {
    super(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {   
    return AuthorityUtils.createAuthorityList(getRole());
  }

  
  @Override
  public boolean isAccountNonExpired() {
      return true;
  }

  @Override
  public boolean isAccountNonLocked() {
      return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true;
  }

  @Override
  public boolean isEnabled() {
      return true;
  }

}
