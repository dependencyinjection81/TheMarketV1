package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.Role;
import com.market.entities.User;
import com.market.entities.VerificationToken;
import com.market.repositories.RoleRepository;
import com.market.repositories.TokenRepository;
import com.market.repositories.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

  
  @Transactional
  public int registerNewUserAccount(final UserForm userForm) {

    if (userNameExist(userForm.getUname())) {
      
      return 1;
 
    } else if (emailExist(userForm.getEmail())) {

      return 2;
   
    } else {
      
      User user = new User();
     
      user.setUsername(userForm.getUname());
      user.setEmail(userForm.getEmail());
      user.setPassword(passwordEncoder.encode(userForm.getPwd()));
      addRoleToUser(user, 30l);
      user.setEnabled(true);      
      saveUser(user); 
      return 0;     
    }
  }
   
  public void createVerificationTokenForUser(final User user, final String token) {
    final VerificationToken myToken = new VerificationToken(token, user);
    tokenRepository.save(myToken);
  }
  
  public int verifyUser(final String currentUsername, final String incomingToken) {

    User clientUser = userRepository.findByEmail(currentUsername);
    VerificationToken repositoryToken = tokenRepository.findByToken(incomingToken);    
    
    if (repositoryToken == null) {
    
      return 1; /*Wrong token*/
    
    } else {
    
      User repositoryUser = repositoryToken.getUser();
      
      if (!clientUser.equals(repositoryUser)) {
      
        return 1; /*Wrong token*/
      
      } else {
        
        final Calendar calNow = Calendar.getInstance();
        calNow.setTimeInMillis(new Date().getTime());
        Long now = calNow.getTimeInMillis();

        final Calendar calExp = Calendar.getInstance();
        calExp.setTimeInMillis(repositoryToken.getExpiryDate().getTime());
        Long exp = calExp.getTimeInMillis();
        
        if (now >= exp) {
        
          return 2; /*Token has expired*/
        
        } else {
          
          addRoleToUser(repositoryUser, 10l); //TODO die Role ID immutable machen
          saveUser(repositoryUser);
          //TODO Delete Token from repository.
          
          return 0; /*Successfully verified*/
        }
        
      }
    }
      
  }
    
  
  private void saveUser(final User user) {
    userRepository.save(user);
  }
 
  private void addRoleToUser(final User user, final Long roleId) {
 
    Optional<Role> findById = roleRepository.findById(roleId);      
    if(!findById.isPresent()) {
      // TODO error;
    }    
    Set<Role> roles = new HashSet<>();
    roles.add(findById.get());
    user.setRoles(roles);
  }
  
  @SuppressWarnings("unused")
  private void deleteRoleFromUser(final User user, final Long roleId) {
    
  }
  
  private boolean userNameExist(final String userName) {
    User user = userRepository.findByUsername(userName);
    
    if (user != null) {
      return true;
    }
    return false;
  }
  
  private boolean emailExist(final String email) {    
    User user = userRepository.findByEmail(email);
    if (user != null) {
      return true;
    }
    return false;
  }
  
}
