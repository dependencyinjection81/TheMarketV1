package com.market.service;

import com.market.beans.RequestForm;
import com.market.entities.User;
import com.market.entities.UserRequest;
import com.market.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
@Service
public class RequestService {
  
  @Autowired
  UserRepository userRepository;

  /**
   * create a new Request.
   * @param requestForm Bean backed form
   * @return
   */
  public boolean createNewRequest(final RequestForm requestForm) {
    
    /**The clumsy workaround to retrieve the currently authenticated user as object.
     * TODO try to find a better solution for retriveing the currently authenticated user as object
     */
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = auth.getName();
    User clientUser = userRepository.findByEmail(currentUsername);
    
    UserRequest userRequest = new UserRequest();
    userRequest.setTitle(requestForm.getTitle());
    userRequest.setDescription(requestForm.getText());
    clientUser.addRequest(userRequest);
    userRepository.save(clientUser);
    //RequestEntity request = new RequestEntity()
    
    return true;
  }
  
}
