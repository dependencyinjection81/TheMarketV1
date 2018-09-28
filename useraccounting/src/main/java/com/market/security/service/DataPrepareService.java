package com.market.security.service;

import com.market.beans.VcodeForm;

/**
 * This service is IMMUTABLE
 * It extracts given parameters provided by the client in the AccountVerification-form
 * and provides cleaned output.
 * @author Johannes Weiss
 *
 */
public class DataPrepareService {
  
  private final String token;

  public DataPrepareService(final VcodeForm vCodeForm ) {
    this.token = extractData(vCodeForm);
  }

  /**
   * Ensures that the token recieved by the client has the expected format.
   * @param vCodeForm Account verification-form.
   * @return the cleared extracted token.
   */
  private String extractData(final VcodeForm vCodeForm) {

    String tokenGiven = vCodeForm.getC1() + vCodeForm.getC2() + vCodeForm.getC3()
                      + vCodeForm.getC4() + vCodeForm.getC5() + vCodeForm.getC6();

    String tokenPrepared = null;

    if (tokenGiven.length() != 6) {
      tokenPrepared = "000000";

    } else {

      tokenPrepared = tokenGiven;
      Integer tokenAsInt = 000000;
      
      try {
        tokenAsInt = Integer.parseInt(tokenPrepared);
      } catch (NumberFormatException e) {
        return "000000";
      }
      
      tokenPrepared = tokenAsInt.toString();
      return tokenPrepared;

    }

    return tokenPrepared;
  
  }
  
  public String getToken() {
    return this.token;
  }
  
}
