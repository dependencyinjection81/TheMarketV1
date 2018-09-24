package com.market.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    boolean isEnabled = userDetails.isEnabled();
    boolean isAccountNonExpired = userDetails.isAccountNonExpired();
    boolean isCredentialsNonExpired = userDetails.isCredentialsNonExpired();
    boolean isAccountNonLocked = userDetails.isAccountNonLocked();
    
    if(!isEnabled) {
      //TODO redirect to verification
    }
    
  }

}
