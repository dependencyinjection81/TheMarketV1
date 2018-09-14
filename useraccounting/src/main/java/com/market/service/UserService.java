package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.Role;
import com.market.entities.User;
import com.market.repositories.RoleRepository;
import com.market.repositories.UserRepository;
import com.market.security.service.TokenService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
  private RoleRepository roleRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

 
  @Override
  public User findByUsername(final String userName) {
    return userRepository.findByUsername(userName);  
  }

  public User findByEmail(final String email) {
    return userRepository.findByEmail(email);
  }
  
  public User findByConfirmationToken(final String confirmationToken) {
    return userRepository.findByConfirmationToken(confirmationToken);
  }

  @Transactional
  @Override
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
      
      //TODO 
      Optional<Role> findById = roleRepository.findById(20l);
      
      if(!findById.isPresent()) {
        // TODO error;
      }
      
      Set<Role> roles = new HashSet<>();
      roles.add(findById.get());
      usr.setRoles(roles);
      
      // Disable user until they confirm the verification code sent by Email.
      usr.setEnabled(false);      
      // Generate random 8-character verification code. 
      usr.setConfirmationToken(new TokenService(8).getToken());
      
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
