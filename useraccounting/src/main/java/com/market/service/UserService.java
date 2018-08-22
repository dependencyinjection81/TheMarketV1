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
   * @param userRepository userRepository.
   */
  @Autowired
  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity findByEmail(final String email) {
    return userRepository.findByEmail(email);
  }

  public UserEntity findByConfirmationToken(final String confirmationToken) {
    return userRepository.findByConfirmationToken(confirmationToken);
  }
 
  private void saveUser(final UserEntity user) {
    userRepository.save(user);
  }

  @Transactional
  @Override
  public UserEntity registerNewUserAccount(final UserForm userForm) {
    
    /**
     * Populating the user entity.
     */
    UserEntity usr = new UserEntity();
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
    return usr; /*TODO warum brauch ich das usr objekt nochmal?*/
  }


}
