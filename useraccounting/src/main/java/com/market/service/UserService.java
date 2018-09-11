package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.UserEntity;
import com.market.repositories.UserRepository;
import com.market.security.service.TokenService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService implements IUserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

 
  @Override
  public UserEntity findByUsername(final String userName) {
    return userRepository.findByUsername(userName);
   
  }

  public UserEntity findByEmail(final String email) {
    return userRepository.findByEmail(email);
  }
  
  public UserEntity findByConfirmationToken(final String confirmationToken) {
    return userRepository.findByConfirmationToken(confirmationToken);
  }

  @Transactional
  @Override
  public int registerNewUserAccount(final UserForm userForm) {

    if (userNameExist(userForm.getUname())) {   
      return 1;
 
    } else if (emailExist(userForm.getEmail())) {
      return 2;
   
    } else {

      /**
       * Everything seems to be ok.
       * Creating and Populating the user entity and finally save it to the repository.
       */
      
      UserEntity usr = new UserEntity();
     
      usr.setUsername(userForm.getUname());
      usr.setEmail(userForm.getEmail());
      usr.setPassword(passwordEncoder.encode(userForm.getPwd()));
      usr.setRole("ROLE_USER");
      // Disable user until they confirm the verification code sent by Email.
      usr.setEnabled(false);      
      // Generate random 8-character verification code. 
      usr.setConfirmationToken(new TokenService(8).getToken());
      
      /**
       * Save entity to repository
       */
      saveUser(usr);
      return 0;
      
    }
  }
  
  
  
  private void saveUser(final UserEntity user) {
    userRepository.save(user);
  }

  private boolean userNameExist(final String userName) {
    UserEntity user = userRepository.findByUsername(userName);
    
    if (user != null) {
      return true;
    }
    return false;
  }
  
  private boolean emailExist(final String email) {    
    UserEntity user = userRepository.findByEmail(email);
    if (user != null) {
      return true;
    }
    return false;
  }
  
}
