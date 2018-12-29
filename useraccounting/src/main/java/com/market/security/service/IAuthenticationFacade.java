package com.market.security.service;

import org.springframework.security.core.Authentication;

/**
 *TODO überall wo die userinformationen aufgerufen werden über dieses Interface gehen.
 * @author Johannes Weiß
 *
 */
public interface IAuthenticationFacade {
  
  Authentication getAuthentication();

}
