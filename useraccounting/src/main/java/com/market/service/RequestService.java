package com.market.service;

import com.market.beans.RequestForm;
import com.market.entities.User;
import com.market.entities.UserRequest;
import com.market.repositories.UserRepository;
import com.market.security.service.IAuthenticationFacade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
  
  @Autowired
  private MessageSource messageSource;

  /**
   * TODO further checks and proper return-value
   * create a new Request.
   * @param requestForm Bean backed form   
   * @return true
   */
  public boolean createNewRequest(final RequestForm requestForm) {
    
    UserRequest userRequest = new UserRequest();
    userRequest.setTitle(requestForm.getTitle());
    userRequest.setDescription(requestForm.getText());
    userRequest.setUrgency(requestForm.getUrgency());
    
    /**
     * Workaraound to get the current Timestamp.
     */
    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();
    Timestamp currentTimestamp = new Timestamp(now.getTime());
    
    userRequest.setTimestamp(currentTimestamp);
    
    Authentication auth = authenticationFacade.getAuthentication();
    User clientUser = userRepository.findByEmail(auth.getName());
    
    clientUser.addRequest(userRequest);
    userRepository.save(clientUser);    
    return true;
  }

  /**
   * Get Requests from current client.
   * @return the personal UserRequests of the current client.
   */
  public List<UserRequest> getMyRequests() {
    
    // TODO use this workaround to retrieve the client userobject everywhere
    // TODO This is a critical operation in terms of security
    Authentication auth = authenticationFacade.getAuthentication();
    User clientUser = userRepository.findByEmail(auth.getName());
   
    
    List<UserRequest> myRequests = new ArrayList<>();
    myRequests.addAll(clientUser.getRequests());
    for (UserRequest r : myRequests) {
      
      String elapsedTime = calculateEclapsedTime(r.getTimestamp());
      r.setElapsedtime(elapsedTime);
    }
    return myRequests;
  }

  /**
   * Helper to generate a readable string for the elapsed time.
   * @param timestamp Timestamp
   * @return elapsed time as readable string
   */
  private String calculateEclapsedTime(final Timestamp timestamp) {
    
    long start = timestamp.getTime();
    /**
     * Workaraound to get the current Timestamp.
     */
    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();
    Timestamp finishAsTimestamp = new Timestamp(now.getTime());
    long finish = finishAsTimestamp.getTime();
    
    long elapsedTimeInMillis = finish - start;
    
    /**
     * Defining the thresholds for minute hour day.
     */
    long minuteInMillis = 60 * 1000;
    long hourInMillis = minuteInMillis * 60;
    long dayInMillis = hourInMillis * 24;
    
    if (elapsedTimeInMillis <= minuteInMillis) {
      
      return messageSource
          .getMessage("info.elapsedtime.seconds", null, LocaleContextHolder.getLocale());  
    
    } else if ((elapsedTimeInMillis > minuteInMillis) && (elapsedTimeInMillis <= hourInMillis)) {
      
      Integer minutes = (int) (elapsedTimeInMillis / minuteInMillis);
      final String[] params = new String[] {minutes.toString()};
      return messageSource
          .getMessage("info.elapsedtime.minutes", params, LocaleContextHolder.getLocale());
    
    } else if ((elapsedTimeInMillis > hourInMillis) && (elapsedTimeInMillis <= dayInMillis)) {
      
      Integer hours = (int) (elapsedTimeInMillis / hourInMillis);
      final String[] params = new String[] {hours.toString()};
      return messageSource
          .getMessage("info.elapsedtime.hours", params, LocaleContextHolder.getLocale());
      
    } else if (elapsedTimeInMillis > dayInMillis) {
      
      Integer days = (int) (elapsedTimeInMillis / dayInMillis);
      final String[] params = new String[] {days.toString()};
      return messageSource
          .getMessage("info.elapsedtime.days", params, LocaleContextHolder.getLocale());
    
    } else {
      return messageSource
          .getMessage("info.elapsedtime.error", null, LocaleContextHolder.getLocale());
    }
  }
  
}
