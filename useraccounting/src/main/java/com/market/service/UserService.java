package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.Role;
import com.market.entities.User;
import com.market.entities.VerificationToken;
import com.market.repositories.RoleRepository;
import com.market.repositories.TokenRepository;
import com.market.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  TokenRepository tokenRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  ApplicationEventPublisher eventPublisher;

  
  /**
   * Find all users.
   * TODO gefährlich! Nicht einfach so durchgeben!
   * @return
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }
  
  /**
   * Find all online users.
   * TODO evetuell nur die usernamen als string rausholen und zurückgeben
   * @return
   */
  public List<String> findAllOnlineUsers() {
    List<String> onlineUsersHard = new ArrayList<>();
    onlineUsersHard.clear();
    onlineUsersHard.addAll(userRepository.findAllOnlineUsers());
    System.out.println(onlineUsersHard.toString());
    List<String> onlineUsersSoft = new ArrayList<>();
    for (String u: onlineUsersHard) {
      onlineUsersSoft.add(u);
    }
    System.out.println(onlineUsersSoft.toString());
    return onlineUsersSoft;
  }
  
  /**
   * Register a new user account.
   * @param userForm User form.
   * @return
   */
  @Transactional
  public boolean registerNewUserAccount(final UserForm userForm) {

    User user = new User();
    user.setUsername(userForm.getUname());
    user.setEmail(userForm.getEmail());
    user.setPassword(passwordEncoder.encode(userForm.getPwd()));
    addRoleToUser(user, 30L);
    user.setEnabled(true);
    saveUser(user);
    /* Status */
    return true;
  }

  /**
   * Check if the given username already exist.
   * @param userName username.
   * @return
   */
  public boolean userNameExist(final String userName) {
    User user = userRepository.findByUsername(userName);
    if (user != null) {
      return true;
    }
    return false;
  }

  /**
   * Check if the given email exist.
   * @param email Email adress.
   * @return
   */
  public boolean emailExist(final String email) {
    User user = userRepository.findByEmail(email);
    if (user != null) {
      return true;
    }
    return false;
  }

  /**
   * Save a verification token to a user.
   * @param user User
   * @param token Token
   */
  public void createVerificationTokenForUser(final User user, final String token) {
    final VerificationToken myToken = new VerificationToken(token, user);
    tokenRepository.save(myToken);
  }

  /**
   * Verify a new useraccount with the given token.
   * @param currentUsername current authenticated user.
   * @param incomingToken given token from client.
   * @param auth current auth object.
   * @return
   */
  public int verifyUser(final String currentUsername, final String incomingToken,
      final Authentication auth) {

    User clientUser = userRepository.findByEmail(currentUsername);
    VerificationToken repositoryToken = tokenRepository.findByToken(incomingToken);

    if (repositoryToken == null) {

      return 1; /* Wrong token */

    } else {

      User repositoryUser = repositoryToken.getUser();

      if (!clientUser.equals(repositoryUser)) {

        return 1; /* Wrong token */

      } else {

        final Calendar calNow = Calendar.getInstance();
        calNow.setTimeInMillis(new Date().getTime());
        Long now = calNow.getTimeInMillis();

        final Calendar calExp = Calendar.getInstance();
        calExp.setTimeInMillis(repositoryToken.getExpiryDate().getTime());
        Long exp = calExp.getTimeInMillis();

        if (now >= exp) {

          return 2; /* Token has expired */

        } else {

          addRoleToUser(repositoryUser, 10L); // TODO die Roles als Enum implementieren
          saveUser(repositoryUser);
          updateRoleInSession(repositoryUser, 10L, auth);
          // TODO Delete Token from repository.

          return 0; /* Successfully verified */
        }

      }
    }

  }
  
  /**
   * Set a given user online.
   * @param email Email
   */
  public void setUserOnline(final String email) {
    userRepository.setUserOnline(email);
  }
  
  /**
   * Set a given user offline.
   * @param email Email
   */
  public void setUserOffline(final String email) {
    userRepository.setUserOffline(email);
  }


  private void saveUser(final User user) {
    userRepository.save(user);
  }

  private void addRoleToUser(final User user, final Long roleId) {

    
    Optional<Role> findById = roleRepository.findById(roleId);
    if (!findById.isPresent()) {
      // TODO error;
    }
    Set<Role> roles = new HashSet<>();
    roles.add(findById.get());
    user.setRoles(roles);   
  }
  
  private void updateRoleInSession(final User user, final Long roleId, final Authentication auth) {
    
    // TODO try to make this simpler and more reliable
    List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
    String role = roleRepository.findById(roleId).get().getName();
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
    updatedAuthorities.add(simpleGrantedAuthority); 
    
    Authentication newAuth = new UsernamePasswordAuthenticationToken(
        auth.getPrincipal(), 
        auth.getCredentials(), 
        updatedAuthorities);
    SecurityContextHolder.getContext().setAuthentication(newAuth);
  }
  

  @SuppressWarnings("unused")
  private void deleteRoleFromUser(final User user, final Long roleId) {
    // TODO implementation
  }

  

}
