package com.market.events;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.market.service.LoginAttemptService;

/**
 * Listener for the AuthenticationFailureBadCredentialsEvent The corresponding event is implemented
 * in spring core itself.
 * 
 * @author Johannes Weiss
 */
@Component
public class AuthenticationFailureListener
    implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private LoginAttemptService loginAttemptService;

  /**
   * Get the clients IP-Adress on login-failure.
   */
  @Override
  public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) {

    System.out.println("!!!EMERGENCY!!!" + e.toString());
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      loginAttemptService.loginFailed(request.getRemoteAddr());
    } else {
      loginAttemptService.loginFailed(xfHeader.split(",")[0]);
    }
  }

}