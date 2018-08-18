package com.market.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class VcodeForm {

  /************************************************************
   * Verification Code.****************************************
   ************************************************************
   */
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Size(min = 8, max = 8, message = "{VcodeForm.vcode.Size.message}")
  private String vcode;

  public String getVcode() {
    return this.vcode;
  }

  public void setVcode(final String vcode) {
    this.vcode = vcode;
  }

}
