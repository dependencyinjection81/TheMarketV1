package com.market.beans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;


public class VcodeForm {

  /************************************************************
   * Verification Code.****************************************
   ************************************************************
   */
  
  @NotBlank
  @Digits(integer=1, fraction=0)
  private String c1;
  @NotBlank
  @Digits(integer=1, fraction=0)
  private String c2;
  @NotBlank
  @Digits(integer=1, fraction=0)
  private String c3;
  @NotBlank
  @Digits(integer=1, fraction=0)
  private String c4;
  @NotBlank
  @Digits(integer=1, fraction=0)
  private String c5;
  @NotBlank
  @Digits(integer=1, fraction=0)
  private String c6;
  
  private String c7;
  
  
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
  
  public String getC7() {
    return c7;
  }
  public void setC7(final String c7) {
    this.c7 = "error";
  }
  
  
}
