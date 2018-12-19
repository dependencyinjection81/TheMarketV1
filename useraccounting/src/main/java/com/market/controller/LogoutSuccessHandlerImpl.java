package com.market.controller;

import com.market.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * TODO SimpleUrlLogoutSuccessHandler implementieren da er noch nicht das event erfasst
 * @author Johannes Weiß
 *
 */
@Component("logoutSuccessHandlerImpl")
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

  @Autowired
  private UserService userService;

  @Override
  public void onLogoutSuccess(final HttpServletRequest req, final HttpServletResponse res,
      final Authentication auth) throws IOException, ServletException {
    HttpSession session = req.getSession();
    if (session != null) {
      session.removeAttribute("user");
      System.out.println(auth.getName());
      userService.setUserOffline(auth.getName());
    }

  }
}
