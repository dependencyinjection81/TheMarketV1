package com.market.beans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;


public class VcodeForm {

  /************************************************************
   * Verification Code.****************************************
   ************************************************************
   */
  
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Digits(integer=1, fraction=0, message ="{VcodeForm.vcode.noDigit.message}")
  private String c1;
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Digits(integer=1, fraction=0, message ="{VcodeForm.vcode.noDigit.message}")
  private String c2;
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Digits(integer=1, fraction=0, message ="{VcodeForm.vcode.noDigit.message}")
  private String c3;
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Digits(integer=1, fraction=0, message ="{VcodeForm.vcode.noDigit.message}")
  private String c4;
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Digits(integer=1, fraction=0, message ="{VcodeForm.vcode.noDigit.message}")
  private String c5;
  @NotBlank(message = "{VcodeForm.vcode.NotBlank.message}")
  @Digits(integer=1, fraction=0, message ="{VcodeForm.vcode.noDigit.message}")
  private String c6;
  
  public String getC1() {
    return c1;
  }
  public void setC1(final String c1) {
    this.c1 = c1;
  }
  public String getC2() {
    return c2;
  }
  public void setC2(final String c2) {
    this.c2 = c2;
  }
  public String getC3() {
    return c3;
  }
  public void setC3(final String c3) {
    this.c3 = c3;
  }
  public String getC4() {
    return c4;
  }
  public void setC4(final String c4) {
    this.c4 = c4;
  }
  public String getC5() {
    return c5;
  }
  public void setC5(final String c5) {
    this.c5 = c5;
  }
  public String getC6() {
    return c6;
  }
  public void setC6(final String c6) {
    this.c6 = c6;
  }
  
  
}
