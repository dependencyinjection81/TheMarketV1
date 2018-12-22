package com.market.controller;

import com.market.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Logout.
 * @author Johannes Weiß
 */
@Component("logoutSuccessHandlerImpl")
public class LogoutSuccessHandlerImpl extends SimpleUrlLogoutSuccessHandler
    implements LogoutSuccessHandler {

  @Autowired
  private UserService userService;

  @Override
  public void onLogoutSuccess(final HttpServletRequest req, final HttpServletResponse res,
      final Authentication auth) throws IOException, ServletException {
    userService.setUserOffline(auth.getName());

    // Redirect to login after logout
    // TODO logout success page for 5 seconds
    String url = req.getContextPath() + "/login";
    res.setStatus(HttpStatus.OK.value());
    res.sendRedirect(url);
  }

}
