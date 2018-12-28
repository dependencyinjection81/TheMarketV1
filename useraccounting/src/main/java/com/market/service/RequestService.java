package com.market.service;

import com.market.beans.RequestForm;
import com.market.entities.User;
import com.market.entities.UserRequest;
import com.market.repositories.UserRepository;
import com.market.security.service.IAuthenticationFacade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
@Service
public class RequestService {
  
  @Autowired
  private IAuthenticationFacade authenticationFacade;
  
  @Autowired
  UserRepository userRepository;

  /**
   * create a new Request.
   * @param requestForm Bean backed form   
   * @return
   */
  public boolean createNewRequest(final RequestForm requestForm) {
    
    Authentication auth = authenticationFacade.getAuthentication();
    User clientUser = userRepository.findByEmail(auth.getName());
    
    UserRequest userRequest = new UserRequest();
    userRequest.setTitle(requestForm.getTitle());
    userRequest.setDescription(requestForm.getText());
    clientUser.addRequest(userRequest);
    userRepository.save(clientUser);
    //RequestEntity request = new RequestEntity()
    
    return true;
  }

  /**
   * Get Requests from current client.
   * @return
   */
  public List<UserRequest> getMyRequests() {
    
    // TODO use this workaround everywhere!
    Authentication auth = authenticationFacade.getAuthentication();
    
    User clientUser = userRepository.findByEmail(auth.getName());
    return clientUser.getRequests();
  }
  
}
