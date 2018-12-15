package com.market.security.validation.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class RepeatMatcher {

  /**
   * Findes out if the given string matches any repeating patterns.
   * @param inputStr input string
   * @param maxLength max length
   * @return
   */
  public String match(final String inputStr, final int maxLength) {

    Pattern greedy = Pattern.compile("(.+)\\1+");
    Pattern lazy = Pattern.compile("(.+?)\\1+");
    Pattern lazyAnchored = Pattern.compile("^(.+?)\\1+$");
    int lastIndex = 0;
    Matcher greedyMatch = greedy.matcher(inputStr);
    Matcher lazyMatch = lazy.matcher(inputStr);
    while (lastIndex < inputStr.length()) {
      if (!greedyMatch.find()) {
        break;
      }
      Matcher match;
      String repeatCharacters;
      boolean lazyMatchFind = lazyMatch.find();
      if (greedyMatch.group(0).length() > (lazyMatchFind ? lazyMatch.group(0).length() : 0)) {
        match = greedyMatch;
        Matcher matcher = lazyAnchored.matcher(match.group(0));
        repeatCharacters = matcher.find() ? matcher.group(1) : match.group(1);
      } else {
        match = lazyMatch;
        repeatCharacters = match.group(1);
      }
      if (repeatCharacters != null && repeatCharacters.length() >= maxLength) {
        return repeatCharacters;
      }
      lastIndex = match.end(0);
    }
    return "";
  }

  /**
   * Description is missing.
   * @param inputStr Password
   * @param maxLength Max length
   * @param times How many times a pattern is allowed
   * @return
   */
  public String match(final String inputStr, final int maxLength, final int times) {

    Pattern greedy = Pattern.compile("(.+)\\1+");
    Pattern lazy = Pattern.compile("(.+?)\\1+");
    Pattern lazyAnchored = Pattern.compile("^(.+?)\\1+$");
    int lastIndex = 0;
    Matcher greedyMatch = greedy.matcher(inputStr);
    Matcher lazyMatch = lazy.matcher(inputStr);
    while (lastIndex < inputStr.length()) {
      if (!greedyMatch.find()) {
        break;
      }
      Matcher match;
      String repeatCharacters;
      boolean lazyMatchFind = lazyMatch.find();
      if (greedyMatch.group(1).length() < (lazyMatchFind ? lazyMatch.group(1).length() : 0)) {
        match = greedyMatch;
        Matcher matcher = lazyAnchored.matcher(match.group(0));
        repeatCharacters = matcher.find() ? matcher.group(1) : match.group(1);
      } else {
        match = lazyMatch;
        repeatCharacters = match.group(1);
      }
      if (repeatCharacters != null && repeatCharacters.length() >= maxLength) {
        if (match.group(0).length() / match.group(1).length() >= times) {
          return repeatCharacters;
        }
      }
      lastIndex = match.end(0);
    }
    return "";
  }
  
}
