package com.market.controller;

import com.market.service.UserService;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessHandlerImpl")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  @Autowired
  private UserService userService;
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  /**
   * Entry point right after a user has logged in.
   */
  @Override
  public void onAuthenticationSuccess(final HttpServletRequest req, final HttpServletResponse res,
      final Authentication auth) throws IOException {

    handle(req, res, auth);
    clearAuthenticationAttributes(req);
  }

  /**
   * Handles further logic when a user has successfully logged in.
   * @param req Request
   * @param res Response
   * @param auth Authentication
   * @throws IOException Exception
   */
  protected void handle(final HttpServletRequest req, final HttpServletResponse res,
      final Authentication auth) throws IOException {

    String targetUrl = determineTargetUrl(auth);

    if (res.isCommitted()) {
      return;
    }

    redirectStrategy.sendRedirect(req, res, targetUrl);
  }

  /**
   * Determine the target url depending on user-status.
   * @param authentication Authentication
   * @param req Request
   * @return
   */
  protected String determineTargetUrl(final Authentication auth) {
    boolean isUserNotVerified = false;
    boolean isUserVerified = false;

    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    for (GrantedAuthority grantedAuthority : authorities) {
      if (grantedAuthority.getAuthority().equals("ROLE_USERNOTVERIFIED")) {
        isUserNotVerified = true;
        break;
      } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
        isUserVerified = true;
        break;
      }
    }

    if (isUserNotVerified) {
      return "/signup-verification";
    } else if (isUserVerified) {
      
      /**
       * Update the online status.
       */
      userService.setUserOnline(auth.getName());
      
      return "/welcome";
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * Clears all AuthenticationExceptions in session.
   * @param req Request
   */
  protected void clearAuthenticationAttributes(final HttpServletRequest req) {
    HttpSession session = req.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

  /**
   * Setter.
   * @param redirectStrategy RedirectStrategy
   */
  public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
    this.redirectStrategy = redirectStrategy;
  }

  /**
   * Getter.
   * @return this RedirectStrategy
   */
  protected RedirectStrategy getRedirectStrategy() {
    return redirectStrategy;
  }
}
