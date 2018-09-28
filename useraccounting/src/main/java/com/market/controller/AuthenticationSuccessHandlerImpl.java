package com.market.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  /**
   * 
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    handle(request, response, authentication);
    clearAuthenticationAttributes(request);
  }

  /**
   * 
   * @param request
   * @param response
   * @param authentication
   * @throws IOException
   */
  protected void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    String targetUrl = determineTargetUrl(authentication);

    if (response.isCommitted()) {
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  /**
   * 
   * @param authentication
   * @return
   */
  protected String determineTargetUrl(Authentication authentication) {
    boolean isUserNotVerified = false;
    boolean isUserVerified = false;

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
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
      return "/verification";
    } else if (isUserVerified) {
      return "/welcome";
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * 
   * @param request
   */
  protected void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
    this.redirectStrategy = redirectStrategy;
  }

  protected RedirectStrategy getRedirectStrategy() {
    return redirectStrategy;
  }
}
