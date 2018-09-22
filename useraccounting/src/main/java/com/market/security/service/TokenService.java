package com.market.security.service;

import java.security.SecureRandom;

public class TokenService {

  private final String token;

  public TokenService() {
    this.token = createPin(6);
  }

  public String getToken() {
    return this.token;
  }

  private String createPin(int length) {
    SecureRandom random = new SecureRandom();
    StringBuilder pass = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      pass.append(random.nextInt(10));
    }
    return pass.toString();
  }

}
