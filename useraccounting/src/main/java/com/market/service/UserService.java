package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.UserEntity;
import com.market.repositories.UserRepository;
import com.market.security.service.TokenService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService implements IUserService {

  private UserRepository userRepository;

  /**
   * CTOR ctor.
   * 
   * @param userRepository
   *          userRepository.
   */
  @Autowired
  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Override
  public UserEntity findByUsername(String userName) {
    return userRepository.findByUserName(userName);
   
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

    UserEntity usr = new UserEntity();
    
    if (userNameExist(userForm.getUname())) {
      usr = null;
    
      return 1;
 
    } else if (emailExist(userForm.getEmail())) {
      usr = null;
      
      return 2;
   
    } else {

      /**
       * Everything seems to be ok.
       * Populating the user entity and finally save it to repository.
       */     
      usr.setUserName(userForm.getUname());
      usr.setEmail(userForm.getEmail());
      usr.setPwd(userForm.getPwd());
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
    UserEntity user = userRepository.findByUserName(userName);
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
