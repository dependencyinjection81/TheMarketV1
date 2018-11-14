package com.market.service;

import com.market.entities.Role;
import com.market.entities.User;
import com.market.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LoginAttemptService loginAttemptService;

  @Autowired
  private HttpServletRequest request;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

    // TODO These booleans aren't in use for now
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    /**
     * Check if the current clients IP has been blocked yet.
     */
    String ip = getClientIp();
    if (loginAttemptService.isBlocked(ip)) {
      //TODO try to reject any further requests from this client.
      throw new RuntimeException("blocked");
    }
    
    /**
     * Try to build a userdetails object by the given username.
     */
    try {

      User user = userRepository.findByEmail(email);
      if (user == null) {
        throw new UsernameNotFoundException("No user found with username: " + email);
      }

      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : user.getRoles()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }

      return new org.springframework.security.core.userdetails.User(user.getEmail(),
          user.getPassword(), user.getEnabled(), accountNonExpired, credentialsNonExpired,
          accountNonLocked, grantedAuthorities);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String getClientIp() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }

}
