package com.market.security.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UsernameValidator {

  /**
   * Makes sure the given form field matches the required criteria.
   * @param uname username
   * @return status
   */
  public int validateUsername(final String uname) {

    if (nullOrEmpty(uname)) {
      return 1;
    } else if (violatedPattern(uname)) {
      return 2;
    } else if (tooShort(uname)) {
      return 3;
    } else if (tooLong(uname)) {
      return 4;
    }
    return 0;
  }

  private boolean nullOrEmpty(final String uname) {

    if (uname == null) {
      return true;
    } else if (uname.equals("")) {
      return true;
    } else if (uname.length() < 1) {
      return true;
    } else {
      return false;
    }
  }

  private boolean violatedPattern(final String uname) {

    Pattern pattern = Pattern.compile("^[a-zA-Z](([a-zA-Z0-9])|[a-zA-Z0-9])*[a-z0-9]$");
    Matcher matcher = pattern.matcher(uname);

    if (!matcher.matches()) {
      return true;
    }
    return false;
  }

  private boolean tooShort(final String uname) {
    if (uname.length() <= 3) {
      return true;
    }
    return false;
  }

  private boolean tooLong(final String uname) {
    if (uname.length() > 20) {
      return true;
    }
    return false;
  }
}
