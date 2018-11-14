package com.market.security.validation;

import com.market.security.validation.helpers.CharSequenceMatcher;
import com.market.security.validation.helpers.KeyboardSequenceMatcher;
import com.market.security.validation.helpers.RepeatMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class PasswordValidator {

  @Autowired
  CharSequenceMatcher charSequenceMatcher;

  @Autowired
  KeyboardSequenceMatcher keyboardSequenceMatcher;

  @Autowired
  RepeatMatcher repeatMatcher;

  private final Integer maxRepeatingSequence = 3;
  private final Integer passwdMinLength = 8;
  private final Integer maxKeyCombinationLength = 3;

  /**
   * Check if the given password matches the minimum criteria.
   * @param pwd password
   * @return 0 if all is OK, 1 if null or empty, 2 if too short
   */
  public String validatePassword(final String pwd) {

    if (nullOrEmpty(pwd)) {
      return "UserForm.pwd.NotBlank";
    } else 
      return checkPwdStrength(pwd);
  }
  
  /**
   * check if two given passwords matches.
   * @param pwd password
   * @param pwdConfirm confirmed password
   * @return 0 password matches, 1 if null or empty, 2 if does not match
   */
  public String fieldMatch(final String pwd, final String pwdConfirm) {
    
    if (nullOrEmpty(pwdConfirm)) {
      return "UserForm.pwdconfirm.NotBlank.message";
    } else if (!pwd.equals(pwdConfirm)) {
      return "UserForm.pwdconfirm.FieldMatch";
    }
    return null;
  }

  private String checkPwdStrength(final String pwd) {
        
    if (!checkPwdLength(pwd)) {
      return "UserForm.pwd.strength.length";
    }
    
    if (!checkPwdComplexity(pwd)) {
      return "UserForm.pwd.strength.complexity";
    }
    
    if (!checkCharSequence(pwd)) {
      return "UserForm.pwd.strength.sequence";
    }
    
    if (!checkForKeyboardCombinations(pwd)) {
      return "UserForm.pwd.strength.combinations";
    }
    if (!checkForRepeatingPatterns(pwd)) {
      return "UserForm.pwd.strength.repeating";
    }
   
    return null;
  }
  
  private boolean nullOrEmpty(final String pwd) {

    if (pwd == null) {
      return true;
    } else if (pwd.equals("")) {
      return true;
    } else if (pwd.length() < 1) {
      return true;
    } else {
      return false;
    }
  }

  private boolean checkPwdLength(final String pwd) {

    if (pwd.length() < passwdMinLength) {
      return false;
    }
    return true;
  }

  private boolean checkPwdComplexity(final String pwd) {
    int countDigit = 0;
    int upperCase = 0;
    int lowerCase = 0;
    int others = 0;
    for (int i = 0; i < pwd.length(); i++) {
      char c = pwd.charAt(i);
      if (Character.isDigit(c)) {
        countDigit = 1;
      } else if (Character.isUpperCase(c)) {
        upperCase = 1;
      } else if (Character.isLowerCase(c)) {
        lowerCase = 1;
      } else {
        others = 1; // Sonderzeichen
      }
    }
    if (countDigit + upperCase + lowerCase + others < 3) {
      return false;
    }
    return true;
  }

  private boolean checkCharSequence(final String pwd) {
    String match = charSequenceMatcher.match(pwd, maxKeyCombinationLength);
    if (!StringUtils.isEmpty(match)) {
      return false;
    }
    return true;
  }

  private boolean checkForKeyboardCombinations(final String pwd) {
    String match = keyboardSequenceMatcher.match(keyboardSequenceMatcher.qwertz, pwd,
        maxKeyCombinationLength);
    if (!StringUtils.isEmpty(match)) {
      return false;
    }
    return true;
  }

  private boolean checkForRepeatingPatterns(final String pwd) {
    String match = repeatMatcher.match(pwd, maxRepeatingSequence);
    if (!StringUtils.isEmpty(match)) {
      return false;
    }
    return true;
  }

}
