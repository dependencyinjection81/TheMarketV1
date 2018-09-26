package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.Role;
import com.market.entities.User;
import com.market.entities.VerificationToken;
import com.market.repositories.RoleRepository;
import com.market.repositories.TokenRepository;
import com.market.repositories.UserRepository;

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
      /**
       * The 1 will be interpreted by the caller (SignupController) to determine further steps.
       */
      return 1;
 
    } else if (emailExist(userForm.getEmail())) {
      /**
       * The 2 will be interpreted by the caller (SignupController) to determine further steps.
       */
      return 2;
   
    } else {

      /**
       * Form Validation has passed with no error.
       * Creating and populating the UserEntity and finally save it to the repository.
       */
      
      User usr = new User();
     
      usr.setUsername(userForm.getUname());
      usr.setEmail(userForm.getEmail());
      usr.setPassword(passwordEncoder.encode(userForm.getPwd()));
      
      /**
       * Adding ROLE_USER to the User
       */
      Optional<Role> findById = roleRepository.findById(30l);      
      if(!findById.isPresent()) {
        // TODO error;
      }    
      Set<Role> roles = new HashSet<>();
      roles.add(findById.get());
      usr.setRoles(roles);
      
      
      usr.setEnabled(true);      
      
      /**
       * Save entity to repository
       */
      saveUser(usr); 
      /**
       * The 0 will be interpreted by the caller (SignupController) to determine further steps.
       */
      return 0;     
    }
  }
   
  public void createVerificationTokenForUser(final User user, final String token) {
    final VerificationToken myToken = new VerificationToken(token, user);
    tokenRepository.save(myToken);
  }
  
  public int verifyUser(final User user, final String token) {
    
    
    
    VerificationToken tokenActual = tokenRepository.findByUser(user);
    VerificationToken tokenGiven = tokenRepository.findByToken(token);
  
    if ((tokenActual != null) && (tokenGiven != null)) {
      
      Date dateNow = new Date();     
      /*if (tokenActual.getExpiryDate() >= dateNow) {
        
      }*/
     
      boolean permitted = (tokenActual.getToken().equals(tokenGiven.getToken()));
      if(permitted) {
        //TODO Change Role
        //Return 0;
      } else {
        return 1; /*Token is not correct*/
      }
    
    } else {
      return 2; /*either no user was found by user or no token was found by token*/
    }
    return 3; /*This should never happen*/
    
  }
  
  
  private void saveUser(final User user) {
    userRepository.save(user);
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
