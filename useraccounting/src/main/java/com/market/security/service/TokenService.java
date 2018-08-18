package com.market.security.service;


public class TokenService {

  private final String token8;
  
  public TokenService(final int length) {
    this.token8 = "abcdefgh";
  }
  
  public String getToken() {
    return this.token8;
  }
  
  
}
