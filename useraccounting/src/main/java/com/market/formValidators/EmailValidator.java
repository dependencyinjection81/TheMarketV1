package com.market.formValidators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {

  /**
   * Validates an Email-adress of a given input from a form field.
   * @param email Email
   * @return
   */
  public int valdateEmail(final String email) {

    if (nullOrEmpty(email)) {
      return 1;
    } else if (violatedPattern(email)) {
      return 2;
    }
    return 0;
  }

  private boolean nullOrEmpty(final String email) {

    if (email == null) {
      return true;
    } else if (email.equals("")) {
      return true;
    } else if (email.length() < 1) {
      return true;
    } else {
      return false;
    }
  }

  private boolean violatedPattern(final String email) {

    Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*" + "+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'"
        + "*+/=?^_`{|}~-]+)"
        + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
        + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
        + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
        + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
        + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
        + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    Matcher matcher = pattern.matcher(email);

    if (!matcher.matches()) {
      return true;
    }
    return false;
  }

}
