package com.market.security.validation.helpers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class KeyboardSequenceMatcher {

  public final HashMap<Character, String[]> qwerty;

  public final HashMap<Character, String[]> qwertz;

  {
    qwerty = new HashMap<>();
    qwerty.put('0', new String[] { "9(", null, null, "-_", "pP", "oO" });
    qwerty.put('1', new String[] { "`~", null, null, "2@", "qQ", null });
    qwerty.put('2', new String[] { "1!", null, null, "3#", "wW", "qQ" });
    qwerty.put('3', new String[] { "2@", null, null, "4$", "eE", "wW" });
    qwerty.put('4', new String[] { "3#", null, null, "5%", "rR", "eE" });
    qwerty.put('5', new String[] { "4$", null, null, "6^", "tT", "rR" });
    qwerty.put('6', new String[] { "5%", null, null, "7&", "yY", "tT" });
    qwerty.put('7', new String[] { "6^", null, null, "8*", "uU", "yY" });
    qwerty.put('8', new String[] { "7&", null, null, "9(", "iI", "uU" });
    qwerty.put('9', new String[] { "8*", null, null, "0)", "oO", "iI" });
    qwerty.put('!', new String[] { "`~", null, null, "2@", "qQ", null });
    qwerty.put('"', new String[] { ";:", "[{", "]}", null, null, "/?" });
    qwerty.put('#', new String[] { "2@", null, null, "4$", "eE", "wW" });
    qwerty.put('$', new String[] { "3#", null, null, "5%", "rR", "eE" });
    qwerty.put('%', new String[] { "4$", null, null, "6^", "tT", "rR" });
    qwerty.put('&', new String[] { "6^", null, null, "8*", "uU", "yY" });
    qwerty.put('\'', new String[] { ";:", "[{", "]}", null, null, "/?" });
    qwerty.put('(', new String[] { "8*", null, null, "0)", "oO", "iI" });
    qwerty.put(')', new String[] { "9(", null, null, "-_", "pP", "oO" });
    qwerty.put('*', new String[] { "7&", null, null, "9(", "iI", "uU" });
    qwerty.put('+', new String[] { "-_", null, null, null, "]}", "[{" });
    qwerty.put(',', new String[] { "mM", "kK", "lL", ".>", null, null });
    qwerty.put('-', new String[] { "0)", null, null, "=+", "[{", "pP" });
    qwerty.put('.', new String[] { ",<", "lL", ";:", "/?", null, null });
    qwerty.put('/', new String[] { ".>", ";:", "'\"", null, null, null });
    qwerty.put(':', new String[] { "lL", "pP", "[{", "'\"", "/?", ".>" });
    qwerty.put(';', new String[] { "lL", "pP", "[{", "'\"", "/?", ".>" });
    qwerty.put('<', new String[] { "mM", "kK", "lL", ".>", null, null });
    qwerty.put('=', new String[] { "-_", null, null, null, "]}", "[{" });
    qwerty.put('>', new String[] { ",<", "lL", ";:", "/?", null, null });
    qwerty.put('?', new String[] { ".>", ";:", "'\"", null, null, null });
    qwerty.put('@', new String[] { "1!", null, null, "3#", "wW", "qQ" });
    qwerty.put('A', new String[] { null, "qQ", "wW", "sS", "zZ", null });
    qwerty.put('B', new String[] { "vV", "gG", "hH", "nN", null, null });
    qwerty.put('C', new String[] { "xX", "dD", "fF", "vV", null, null });
    qwerty.put('D', new String[] { "sS", "eE", "rR", "fF", "cC", "xX" });
    qwerty.put('E', new String[] { "wW", "3#", "4$", "rR", "dD", "sS" });
    qwerty.put('F', new String[] { "dD", "rR", "tT", "gG", "vV", "cC" });
    qwerty.put('G', new String[] { "fF", "tT", "yY", "hH", "bB", "vV" });
    qwerty.put('H', new String[] { "gG", "yY", "uU", "jJ", "nN", "bB" });
    qwerty.put('I', new String[] { "uU", "8*", "9(", "oO", "kK", "jJ" });
    qwerty.put('J', new String[] { "hH", "uU", "iI", "kK", "mM", "nN" });
    qwerty.put('K', new String[] { "jJ", "iI", "oO", "lL", ",<", "mM" });
    qwerty.put('L', new String[] { "kK", "oO", "pP", ";:", ".>", ",<" });
    qwerty.put('M', new String[] { "nN", "jJ", "kK", ",<", null, null });
    qwerty.put('N', new String[] { "bB", "hH", "jJ", "mM", null, null });
    qwerty.put('O', new String[] { "iI", "9(", "0)", "pP", "lL", "kK" });
    qwerty.put('P', new String[] { "oO", "0)", "-_", "[{", ";:", "lL" });
    qwerty.put('Q', new String[] { null, "1!", "2@", "wW", "aA", null });
    qwerty.put('R', new String[] { "eE", "4$", "5%", "tT", "fF", "dD" });
    qwerty.put('S', new String[] { "aA", "wW", "eE", "dD", "xX", "zZ" });
    qwerty.put('T', new String[] { "rR", "5%", "6^", "yY", "gG", "fF" });
    qwerty.put('U', new String[] { "yY", "7&", "8*", "iI", "jJ", "hH" });
    qwerty.put('V', new String[] { "cC", "fF", "gG", "bB", null, null });
    qwerty.put('W', new String[] { "qQ", "2@", "3#", "eE", "sS", "aA" });
    qwerty.put('X', new String[] { "zZ", "sS", "dD", "cC", null, null });
    qwerty.put('Y', new String[] { "tT", "6^", "7&", "uU", "hH", "gG" });
    qwerty.put('Z', new String[] { null, "aA", "sS", "xX", null, null });
    qwerty.put('[', new String[] { "pP", "-_", "=+", "]}", "'\"", ";:" });
    qwerty.put('\\', new String[] { "]}", null, null, null, null, null });
    qwerty.put(']', new String[] { "[{", "=+", null, "\\|", null, "'\"" });
    qwerty.put('^', new String[] { "5%", null, null, "7&", "yY", "tT" });
    qwerty.put('_', new String[] { "0)", null, null, "=+", "[{", "pP" });
    qwerty.put('`', new String[] { null, null, null, "1!", null, null });
    qwerty.put('a', new String[] { null, "qQ", "wW", "sS", "zZ", null });
    qwerty.put('b', new String[] { "vV", "gG", "hH", "nN", null, null });
    qwerty.put('c', new String[] { "xX", "dD", "fF", "vV", null, null });
    qwerty.put('d', new String[] { "sS", "eE", "rR", "fF", "cC", "xX" });
    qwerty.put('e', new String[] { "wW", "3#", "4$", "rR", "dD", "sS" });
    qwerty.put('f', new String[] { "dD", "rR", "tT", "gG", "vV", "cC" });
    qwerty.put('g', new String[] { "fF", "tT", "yY", "hH", "bB", "vV" });
    qwerty.put('h', new String[] { "gG", "yY", "uU", "jJ", "nN", "bB" });
    qwerty.put('i', new String[] { "uU", "8*", "9(", "oO", "kK", "jJ" });
    qwerty.put('j', new String[] { "hH", "uU", "iI", "kK", "mM", "nN" });
    qwerty.put('k', new String[] { "jJ", "iI", "oO", "lL", ",<", "mM" });
    qwerty.put('l', new String[] { "kK", "oO", "pP", ";:", ".>", ",<" });
    qwerty.put('m', new String[] { "nN", "jJ", "kK", ",<", null, null });
    qwerty.put('n', new String[] { "bB", "hH", "jJ", "mM", null, null });
    qwerty.put('o', new String[] { "iI", "9(", "0)", "pP", "lL", "kK" });
    qwerty.put('p', new String[] { "oO", "0)", "-_", "[{", ";:", "lL" });
    qwerty.put('q', new String[] { null, "1!", "2@", "wW", "aA", null });
    qwerty.put('r', new String[] { "eE", "4$", "5%", "tT", "fF", "dD" });
    qwerty.put('s', new String[] { "aA", "wW", "eE", "dD", "xX", "zZ" });
    qwerty.put('t', new String[] { "rR", "5%", "6^", "yY", "gG", "fF" });
    qwerty.put('u', new String[] { "yY", "7&", "8*", "iI", "jJ", "hH" });
    qwerty.put('v', new String[] { "cC", "fF", "gG", "bB", null, null });
    qwerty.put('w', new String[] { "qQ", "2@", "3#", "eE", "sS", "aA" });
    qwerty.put('x', new String[] { "zZ", "sS", "dD", "cC", null, null });
    qwerty.put('y', new String[] { "tT", "6^", "7&", "uU", "hH", "gG" });
    qwerty.put('z', new String[] { null, "aA", "sS", "xX", null, null });
    qwerty.put('{', new String[] { "pP", "-_", "=+", "]}", "'\"", ";:" });
    qwerty.put('|', new String[] { "]}", null, null, null, null, null });
    qwerty.put('}', new String[] { "[{", "=+", null, "\\|", null, "'\"" });
    qwerty.put('~', new String[] { null, null, null, "1!", null, null });
  }

  {
    qwertz = new HashMap<>();

    qwertz.put('^', new String[] { null, null, null, "1!", null, null });
    qwertz.put('1', new String[] { "^°", null, null, "2\"", "qQ", null });
    qwertz.put('2', new String[] { "1!", null, null, "3§", "wW", "qQ" });
    qwertz.put('3', new String[] { "2\"", null, null, "4$", "eE", "wW" });
    qwertz.put('4', new String[] { "3§", null, null, "5%", "rR", "eE" });
    qwertz.put('5', new String[] { "4$", null, null, "6&", "tT", "rR" });
    qwertz.put('6', new String[] { "5%", null, null, "7/", "zZ", "tT" });
    qwertz.put('7', new String[] { "6&", null, null, "8(", "uU", "zZ" });
    qwertz.put('8', new String[] { "7/", null, null, "9)", "iI", "uU" });
    qwertz.put('9', new String[] { "8(", null, null, "0=", "oO", "iI" });
    qwertz.put('0', new String[] { "9)", null, null, "ß?", "pP", "oO" });
    qwertz.put('ß', new String[] { "0=", null, null, "´`", "üÜ", "pP" });
    qwertz.put('´', new String[] { "ß?", null, null, null, "+*", "üÜ" });

    qwertz.put('°', new String[] { null, null, null, "1!", null, null });
    qwertz.put('!', new String[] { "^°", null, null, "2\"", "qQ", null });
    qwertz.put('"', new String[] { "1!", null, null, "3§", "wW", "qQ" });
    qwertz.put('§', new String[] { "2\"", null, null, "4$", "eE", "wW" });
    qwertz.put('$', new String[] { "3§", null, null, "5%", "rR", "eE" });
    qwertz.put('%', new String[] { "4$", null, null, "6&", "tT", "rR" });
    qwertz.put('&', new String[] { "5%", null, null, "7/", "zZ", "tT" });
    qwertz.put('/', new String[] { "6&", null, null, "8(", "uU", "zZ" });
    qwertz.put('(', new String[] { "7/", null, null, "9)", "iI", "uU" });
    qwertz.put(')', new String[] { "8(", null, null, "0=", "oO", "iI" });
    qwertz.put('=', new String[] { "9)", null, null, "ß?", "pP", "oO" });
    qwertz.put('?', new String[] { "0=", null, null, "´`", "üÜ", "pP" });
    qwertz.put('`', new String[] { "ß?", null, null, null, "+*", "üÜ" });

    qwertz.put('q', new String[] { null, "1!", "2\"", "wW", "aA", null });
    qwertz.put('w', new String[] { "qQ", "2\"", "3§", "eE", "sS", "aA" });
    qwertz.put('e', new String[] { "wW", "3§", "4$", "rR", "dD", "sS" });
    qwertz.put('r', new String[] { "eE", "4$", "5%", "tT", "fF", "dD" });
    qwertz.put('t', new String[] { "rR", "5%", "6&", "zZ", "gG", "fF" });
    qwertz.put('z', new String[] { "tT", "6&", "7/", "uU", "hH", "gG" });
    qwertz.put('u', new String[] { "zZ", "7/", "8(", "iI", "jJ", "hH" });
    qwertz.put('i', new String[] { "uU", "8(", "9)", "oO", "kK", "jJ" });
    qwertz.put('o', new String[] { "iI", "9)", "0=", "pP", "lL", "kK" });
    qwertz.put('p', new String[] { "oO", "0=", "ß?", "üÜ", "öÖ", "lL" });
    qwertz.put('ü', new String[] { "pP", "ß?", "´`", "+*", "äÄ", "öÖ" });
    qwertz.put('+', new String[] { "üÜ", "´`", null, null, "#'", "äÄ" });

    qwertz.put('Q', new String[] { null, "1!", "2\"", "wW", "aA", null });
    qwertz.put('W', new String[] { "qQ", "2\"", "3§", "eE", "sS", "aA" });
    qwertz.put('E', new String[] { "wW", "3§", "4$", "rR", "dD", "sS" });
    qwertz.put('R', new String[] { "eE", "4$", "5%", "tT", "fF", "dD" });
    qwertz.put('T', new String[] { "rR", "5%", "6&", "zZ", "gG", "fF" });
    qwertz.put('Z', new String[] { "tT", "6&", "7/", "uU", "hH", "gG" });
    qwertz.put('U', new String[] { "zZ", "7/", "8(", "iI", "jJ", "hH" });
    qwertz.put('I', new String[] { "uU", "8(", "9)", "oO", "kK", "jJ" });
    qwertz.put('O', new String[] { "iI", "9)", "0=", "pP", "lL", "kK" });
    qwertz.put('P', new String[] { "oO", "0=", "ß?", "üÜ", "öÖ", "lL" });
    qwertz.put('Ü', new String[] { "pP", "ß?", "´`", "+*", "äÄ", "öÖ" });
    qwertz.put('*', new String[] { "üÜ", "´`", null, null, "#'", "äÄ" });

    qwertz.put('a', new String[] { null, "qQ", "wW", "sS", "yY", null });
    qwertz.put('s', new String[] { "aA", "wW", "eE", "dD", "xX", "yY" });
    qwertz.put('d', new String[] { "sS", "eE", "rR", "fF", "cC", "xX" });
    qwertz.put('f', new String[] { "dD", "rR", "tT", "gG", "vV", "cC" });
    qwertz.put('g', new String[] { "fF", "tT", "zZ", "hH", "bB", "vV" });
    qwertz.put('h', new String[] { "gG", "zZ", "uU", "jJ", "nN", "bB" });
    qwertz.put('j', new String[] { "hH", "uU", "iI", "kK", "mM", "nN" });
    qwertz.put('k', new String[] { "jJ", "iI", "oO", "lL", ",;", "mM" });
    qwertz.put('l', new String[] { "kK", "oO", "pP", "öÖ", ".:", ",;" });
    qwertz.put('ö', new String[] { "lL", "pP", "üÜ", "äÄ", "-_", ".:" });
    qwertz.put('ä', new String[] { "öÖ", "üÜ", "+*", "#'", null, "-_" });
    qwertz.put('#', new String[] { "äÄ", "+*", null, null, null, null });
    qwertz.put('<', new String[] { null, null, "aA", "yY", null, null });
    qwertz.put('y', new String[] { "<>", "aA", "sS", "xX", null, null });
    qwertz.put('x', new String[] { "yY", "sS", "dD", "cC", null, null });
    qwertz.put('c', new String[] { "xX", "dD", "fF", "vV", null, null });
    qwertz.put('v', new String[] { "cC", "fF", "gG", "bB", null, null });
    qwertz.put('b', new String[] { "vV", "gG", "hH", "nN", null, null });
    qwertz.put('n', new String[] { "bB", "hH", "jJ", "mM", null, null });
    qwertz.put('m', new String[] { "nN", "jJ", "kK", ",;", null, null });
    qwertz.put(',', new String[] { "mM", "kK", "lL", ".:", null, null });
    qwertz.put('.', new String[] { ",;", "lL", "öÖ", "-_", null, null });
    qwertz.put('-', new String[] { ".:", "öÖ", "äÄ", null, null, null });

    qwertz.put('A', new String[] { null, "qQ", "wW", "sS", "yY", null });
    qwertz.put('S', new String[] { "aA", "wW", "eE", "dD", "xX", "yY" });
    qwertz.put('D', new String[] { "sS", "eE", "rR", "fF", "cC", "xX" });
    qwertz.put('F', new String[] { "dD", "rR", "tT", "gG", "vV", "cC" });
    qwertz.put('G', new String[] { "fF", "tT", "zZ", "hH", "bB", "vV" });
    qwertz.put('H', new String[] { "gG", "zZ", "uU", "jJ", "nN", "bB" });
    qwertz.put('J', new String[] { "hH", "uU", "iI", "kK", "mM", "nN" });
    qwertz.put('K', new String[] { "jJ", "iI", "oO", "lL", ",;", "mM" });
    qwertz.put('L', new String[] { "kK", "oO", "pP", "öÖ", ".:", ",;" });
    qwertz.put('Ö', new String[] { "lL", "pP", "üÜ", "äÄ", "-_", ".:" });
    qwertz.put('Ä', new String[] { "öÖ", "üÜ", "+*", "#'", null, "-_" });
    qwertz.put('\'', new String[] { "äÄ", "+*", null, null, null, null });
    qwertz.put('>', new String[] { null, null, "aA", "yY", null, null });
    qwertz.put('Y', new String[] { "<>", "aA", "sS", "xX", null, null });
    qwertz.put('X', new String[] { "yY", "sS", "dD", "cC", null, null });
    qwertz.put('C', new String[] { "xX", "dD", "fF", "vV", null, null });
    qwertz.put('V', new String[] { "cC", "fF", "gG", "bB", null, null });
    qwertz.put('B', new String[] { "vV", "gG", "hH", "nN", null, null });
    qwertz.put('N', new String[] { "bB", "hH", "jJ", "mM", null, null });
    qwertz.put('M', new String[] { "nN", "jJ", "kK", ",;", null, null });
    qwertz.put(';', new String[] { "mM", "kK", "lL", ".:", null, null });
    qwertz.put(':', new String[] { ",;", "lL", "öÖ", "-_", null, null });
    qwertz.put('_', new String[] { ".:", "öÖ", "äÄ", null, null, null });
  }

  /**
   * Findes out if the given string matches any keyboard sequence.
   * @param keyMap key map
   * @param inputStr input String
   * @param maxKeyLength max key length
   * @return
   */
  public String match(final HashMap<Character, String[]> keyMap, final String inputStr,
      final int maxKeyLength) {

    Map<Integer, Set<Character>> neighbors = new HashMap<>();
    for (int i = 0; i < inputStr.length(); i++) {
      neighbors.put(i, getNeighbors(keyMap, inputStr.charAt(i)));
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < inputStr.length(); i++) {
      Character character = inputStr.charAt(i);
      int neighborsI = i + 1;
      if (neighborsI < neighbors.size()) {
        boolean added = false;
        for (Character neighbor : neighbors.get(neighborsI)) {
          if (neighbor.equals(character)) {
            builder.append(character);
            added = true;
            break;
          }
        }
        if (!added) {
          builder.append(character);
          if (builder.length() >= maxKeyLength) {
            return builder.toString();
          }
          builder.setLength(0);
        }

      } else {
        builder.append(character);
        if (builder.length() >= maxKeyLength) {
          return builder.toString();
        }
        builder.setLength(0);
      }
    }

    return "";
  }

  private Set<Character> getNeighbors(final Map<Character, String[]> keyMap, final Character key) {
    final Set<Character> neighbors = new HashSet<>();

    if (keyMap.containsKey(key)) {
      String[] tmpNeighbors = keyMap.get(key);
      for (final String tmp_neighbor : tmpNeighbors) {
        if (null == tmp_neighbor) {
          continue;
        }
        for (Character character : tmp_neighbor.toCharArray()) {
          neighbors.add(character);
        }
      }
    }
    return neighbors;
  }

}
