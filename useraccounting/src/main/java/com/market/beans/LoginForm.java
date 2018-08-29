package com.market.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
  
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
   * Email.****************************************************
   ************************************************************
   */
  @NotBlank(message = "{UserForm.email.NotBlank.message}")
  @Email(message = "{UserForm.email.Email.message}")
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
  private String pwd;
  
  public String getPwd() {
    return pwd;
  }

  public void setPwd(final String pwd) {
    this.pwd = pwd;
  }

}
