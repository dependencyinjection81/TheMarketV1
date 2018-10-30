package com.market.security.validation.helpers;

import org.springframework.stereotype.Service;

@Service
public class CharSequenceMatcher {
  
  public String match(final String inputStr, final int maxKeyLength) {
    char[] characters = inputStr.toCharArray();
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < characters.length; i++) {
      Character current_character = characters[i];
      
      if (i + 1 < characters.length) {
        Character next_character = characters[i + 1];
        // Alpha upper case
        if (next_character >= 65 && next_character <= 90) {
          // Forward matches
          if (next_character == current_character + 1) {
            builder.append(current_character);
            continue;
          }
          if (next_character + 32 == current_character + 1) {
            builder.append(current_character);
            continue;
          }
          // Reverse matches
          if (next_character == current_character - 1) {
            builder.append(current_character);
            continue;
          }
          if (next_character + 32 == current_character - 1) {
            builder.append(current_character);
            continue;
          }
        }
        // Alpha lower case
        if (next_character >= 97 && next_character <= 122) {
          // Forward matches
          if (next_character == current_character + 1) {
            builder.append(current_character);
            continue;
          }
          if (next_character - 32 == current_character + 1) {
            builder.append(current_character);
            continue;
          }
          // Reverse matches
          if (next_character == current_character - 1) {
            builder.append(current_character);
            continue;
          }
          if (next_character - 32 == current_character - 1) {
            builder.append(current_character);
            continue;
          }
        }
        // Numeric
        if (next_character >= 48 && next_character <= 57) {
          // Forward matches
          if (next_character == current_character + 1) {
            builder.append(current_character);
            continue;
          }
          // Reverse matches
          if (next_character == current_character - 1) {
            builder.append(current_character);
            continue;
          }
        }
      }

      if (builder.length() > 0) {
        builder.append(current_character);
        if (builder.length() >= maxKeyLength) {
          return builder.toString();
        }
        builder.setLength(0);
      }
    }
    return matchSimple(inputStr, maxKeyLength);
  }
  
  /**
   * Find simple character repetitions like 'aaaaa' '88888'
   * @param inputStr
   * @param maxKeyLength maximum allowed repetitions
   * @return
   */
  private static String matchSimple(final String inputStr, final int maxKeyLength) {
    char[] characters = inputStr.toCharArray();
    StringBuilder builder = new StringBuilder();
    Character last_character = null;
    
    for(int i = 0; i < characters.length; i++) {
      Character current_character = characters[i];
      if(last_character == current_character) {
        builder.append(current_character);
      }
      else {
        builder.setLength(0);
      }
      if(builder.length() >= maxKeyLength) {
        return builder.toString();
      }
      last_character = current_character;
    }
    return "";
  }

}
