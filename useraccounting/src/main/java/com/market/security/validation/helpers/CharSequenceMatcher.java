package com.market.security.validation.helpers;

import org.springframework.stereotype.Service;

@Service
public class CharSequenceMatcher {

  /**
   * Find out if a given string contains a sequence of chars.
   * 
   * @param inputStr     input string
   * @param maxKeyLength max key length
   * @return
   */
  public String match(final String inputStr, final int maxKeyLength) {
    char[] characters = inputStr.toCharArray();
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < characters.length; i++) {
      Character currentCharacter = characters[i];

      if (i + 1 < characters.length) {
        Character nextCharacter = characters[i + 1];
        // Alpha upper case
        if (nextCharacter >= 65 && nextCharacter <= 90) {
          // Forward matches
          if (nextCharacter == currentCharacter + 1) {
            builder.append(currentCharacter);
            continue;
          }
          if (nextCharacter + 32 == currentCharacter + 1) {
            builder.append(currentCharacter);
            continue;
          }
          // Reverse matches
          if (nextCharacter == currentCharacter - 1) {
            builder.append(currentCharacter);
            continue;
          }
          if (nextCharacter + 32 == currentCharacter - 1) {
            builder.append(currentCharacter);
            continue;
          }
        }
        // Alpha lower case
        if (nextCharacter >= 97 && nextCharacter <= 122) {
          // Forward matches
          if (nextCharacter == currentCharacter + 1) {
            builder.append(currentCharacter);
            continue;
          }
          if (nextCharacter - 32 == currentCharacter + 1) {
            builder.append(currentCharacter);
            continue;
          }
          // Reverse matches
          if (nextCharacter == currentCharacter - 1) {
            builder.append(currentCharacter);
            continue;
          }
          if (nextCharacter - 32 == currentCharacter - 1) {
            builder.append(currentCharacter);
            continue;
          }
        }
        // Numeric
        if (nextCharacter >= 48 && nextCharacter <= 57) {
          // Forward matches
          if (nextCharacter == currentCharacter + 1) {
            builder.append(currentCharacter);
            continue;
          }
          // Reverse matches
          if (nextCharacter == currentCharacter - 1) {
            builder.append(currentCharacter);
            continue;
          }
        }
      }

      if (builder.length() > 0) {
        builder.append(currentCharacter);
        if (builder.length() >= maxKeyLength) {
          return builder.toString();
        }
        builder.setLength(0);
      }
    }
    return matchSimple(inputStr, maxKeyLength);
  }

  /**
   * Find simple character repetitions like 'aaaaa' '88888'.
   * 
   * @param inputStr     input String
   * @param maxKeyLength maximum allowed repetitions
   * @return
   */
  private static String matchSimple(final String inputStr, final int maxKeyLength) {
    char[] characters = inputStr.toCharArray();
    StringBuilder builder = new StringBuilder();
    Character lastCharacter = null;

    for (int i = 0; i < characters.length; i++) {
      Character currentCharacter = characters[i];
      if (lastCharacter == currentCharacter) {
        builder.append(currentCharacter);
      } else {
        builder.setLength(0);
      }
      if (builder.length() >= maxKeyLength) {
        return builder.toString();
      }
      lastCharacter = currentCharacter;
    }
    return "";
  }

}
