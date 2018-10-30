package com.market.beans;

import com.market.security.constraint.FieldMatch;

@FieldMatch(first = "pwd", second = "pwdConfirm", 
    message = "{UserForm.pwdconfirm.FieldMatch.message}")
public class UserForm {
  
  /************************************************************
   * Language.*************************************************
   ************************************************************
   */
  
  private String language;

  public String getLanguage() {
    return this.language;
  }
  
  public void setLanguage(final String language) {
    this.language = language;
  }
  

  /************************************************************
   * Username.*************************************************
   ************************************************************
   */
  private String uname;

  public String getUname() {
    return this.uname;
  }

  public void setUname(final String uname) {
    this.uname = uname;
  }

  
  /************************************************************
   * Email.****************************************************
   ************************************************************
   */
  private String email;
  
  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  
  /************************************************************
   * Password.*************************************************
   ************************************************************
   */
  private String pwd;
  
  public String getPwd() {
    return pwd;
  }

  public void setPwd(final String pwd) {
    this.pwd = pwd;
  }

  
  /************************************************************
   * Password confirm.*****************************************
   ************************************************************
   */
  private String pwdConfirm;

  public String getPwdConfirm() {
    return pwdConfirm;
  }

  public void setPwdConfirm(final String pwdConfirm) {
    this.pwdConfirm = pwdConfirm;
  }

}
