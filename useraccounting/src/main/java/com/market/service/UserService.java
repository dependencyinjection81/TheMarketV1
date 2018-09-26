package com.market.service;

import com.market.beans.UserForm;
import com.market.beans.VcodeForm;
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
      
      User user = new User();
     
      user.setUsername(userForm.getUname());
      user.setEmail(userForm.getEmail());
      user.setPassword(passwordEncoder.encode(userForm.getPwd()));
      
      /**
       * Adding ROLE_USER to the User
       */
      Optional<Role> findById = roleRepository.findById(30l);      
      if(!findById.isPresent()) {
        // TODO error;
      }    
      Set<Role> roles = new HashSet<>();
      roles.add(findById.get());
      user.setRoles(roles);
      
      
      user.setEnabled(true);      
      
      /**
       * Save entity to repository
       */
      saveUser(user); 
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
  
  public int verifyUser(final String currentUsername, final VcodeForm vcodeForm) {

    User currentUser = userRepository.findByUsername(currentUsername);
    
    /**
     * Token given by the current logged in user.
     */
    String tokenGiven = vcodeForm.getC1() + vcodeForm.getC2() + vcodeForm.getC3() 
                      + vcodeForm.getC4() + vcodeForm.getC5() + vcodeForm.getC6();
    
    /**
     * Actual Token of the user from repository.
     */
    VerificationToken tokenActual = tokenRepository.findByUser(currentUser);

    if ((tokenActual != null)) {

      final Calendar calNow = Calendar.getInstance();
      calNow.setTimeInMillis(new Date().getTime());
      Long now = calNow.getTimeInMillis();

      final Calendar calExp = Calendar.getInstance();
      calExp.setTimeInMillis(tokenActual.getExpiryDate().getTime());
      Long exp = calExp.getTimeInMillis();

      if (now >= exp) {
        
        return 1; /* Token has expired */

      } else {
        
        boolean permitted = (tokenActual.getToken().equals(tokenGiven));
        
        if (permitted) {
          
          
          // TODO delete ROLE_USERNOTVERIFIED
          // TODO add ROLE_USER
          // TODO delete Token
          
          return 0; /*Successfully verified*/ 
        
        } else {
        
          return 2; /* Wrong token */
        
        }
        
      }

    }
    
    return 3; /*No Token was found for the given User. If this case happned something really bad and unlikely was going on*/
  }
    
  private void saveUser(final User user) {
    userRepository.save(user);
  }
 
  private void addRoleToUser(final Long roleId) {
    
  }
  
  private void deleteRoleFromUser(final Long roleId) {
    
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
