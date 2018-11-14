package com.market.beans;


public class UserForm {
  
  
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
