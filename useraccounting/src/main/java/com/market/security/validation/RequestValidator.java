package com.market.security.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RequestValidator {
  
  private Map<String, Integer> badWords;
  
  
  /**
   * Custom ctor.
   */
  public RequestValidator() {
    this.badWords = new HashMap<>();
  }

  /**
   * Makes sure the given form field matches the required criteria.
   * @param title Request title
   * @return status Status
   */
  public String validateRequestTitle(final String title) {
    
    if (nullOrEmpty(title)) {
      return "RequestForm.title.NotBlank.message";
    } else if (tooShort(title)) {
      return "RequestForm.title.TooShort.message";
    } else if (tooLong(title)) {
      return "RequestForm.title.TooLong.message";
    } else if (toxicTitle(title)) {
      return "RequestForm.title.isToxic.message";
    }
    return null;
  }
  
  private boolean toxicTitle(final String title) {
    // TODO Retrieve badwordlist from database and check for toxication level
    return false;
  }

  private boolean nullOrEmpty(final String title) {

    if (title == null) {
      return true;
    } else if (title.equals("")) {
      return true;
    } else if (title.length() < 1) {
      return true;
    } else {
      return false;
    }
  }
  
  private boolean tooShort(final String title) {
    if (title.length() <= 15) {
      return true;
    }
    return false;
  }

  private boolean tooLong(final String title) {
    if (title.length() > 35) {
      return true;
    }
    return false;
  }

}
