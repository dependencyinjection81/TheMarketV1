package com.market.beans;

import com.market.security.constraint.FieldMatch;
import com.market.security.constraint.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
  @NotBlank(message = "{UserForm.uname.NotBlank.message}")
  @Pattern(regexp = "^[a-zA-Z](([a-zA-Z0-9])|[a-zA-Z0-9])*[a-z0-9]$", message = "{UserForm.uname.Whitelist.message}")
  @Size(min = 3, max = 20, message = "{UserForm.uname.Size.message}")
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
  @NotBlank(message = "{UserForm.email.NotBlank.message}")
  @Email(message = "{UserForm.email.Email.message}")
  //TODO using the standard email-Regex
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
  @NotBlank(message = "{UserForm.pwd.NotBlank.message}")
  @Size(min = 8, message = "{UserForm.pwd.Size.message}")
  @ValidPassword(message = "password policy violated")
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
  @NotBlank(message = "{UserForm.pwdconfirm.NotBlank.message}")
  private String pwdConfirm;

  public String getPwdConfirm() {
    return pwdConfirm;
  }

  public void setPwdConfirm(final String pwdConfirm) {
    this.pwdConfirm = pwdConfirm;
  }

}
