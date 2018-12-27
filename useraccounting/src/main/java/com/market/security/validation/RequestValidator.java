package com.market.security.validation;

import org.springframework.stereotype.Service;

@Service
public class RequestValidator {

  // TODO bad words blacklist
  //private Map<String, Integer> badWords;
  
  private final int minTitleLength = 15;
  private final int maxTitleLength = 35;
  private final int minTextLength = 35;
  private final int maxTextLength = 300;
  
  /**
   * Makes sure the given form field matches the required criteria.
   * @param title Request title
   * @return status Status
   */
  public String validateRequestTitle(final String title) {
    
    if (nullOrEmpty(title)) {
      return "RequestForm.title.NotBlank.message";
    } else if (tooShort(title, this.minTitleLength)) {
      return "RequestForm.title.TooShort.message";
    } else if (tooLong(title, this.maxTitleLength)) {
      return "RequestForm.title.TooLong.message";
    } else if (toxicTitle(title)) {
      return "RequestForm.title.isToxic.message";
    } else {
      return null;  
    }
    
  }
  
  /**
   * Makes sure the given form field matches the required criteria.
   * @param text Request text
   * @return status Status
   */
  public String validateRequestText(final String text) {
    
    if (nullOrEmpty(text)) {
      return "RequestForm.text.NotBlank.message";
    } else if (tooShort(text, this.minTextLength)) {
      return "RequestForm.text.TooShort.message";
    } else if (tooLong(text, this.maxTextLength)) {
      return "RequestForm.text.TooLong.message";
    } else if (toxicTitle(text)) {
      return "RequestForm.text.isToxic.message";
    } else {
      return null;  
    }
    
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
  
  private boolean tooShort(final String title, final int minLength) {
    if (title.length() <= minLength) {
      return true;
    }
    return false;
  }

  private boolean tooLong(final String title, final int maxLength) {
    if (title.length() > maxLength) {
      return true;
    }
    return false;
  }

}
