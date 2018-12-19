package com.market.controller;

import com.market.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("logoutSuccessHandlerImpl")
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

  @Autowired
  private UserService userService;

  @Override
  public void onLogoutSuccess(final HttpServletRequest req, final HttpServletResponse res,
      final Authentication auth) throws IOException, ServletException {
    System.out.println(auth.getName());
    userService.setUserOffline(auth.getName());
  }
}
