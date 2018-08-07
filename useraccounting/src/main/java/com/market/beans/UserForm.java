package com.market.beans;

import com.market.security.constraint.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch(first = "pwd", second = "pwdConfirm", 
    message = "Confirmed password differs from password above!")
public class UserForm {

  @NotBlank(message = "This field cannot be empty!")
  @Email(message = "You must enter a valid Email adress!")
  private String email;
  
  @NotBlank(message = "This field cannot be empty!")
  private String uname;

  @NotBlank(message = "This field cannot be empty!")
  @Size(min = 8, message = "Your Password must have at least 8 characters!")
  private String pwd;

  @NotBlank(message = "You must confirm your password!")
  private String pwdConfirm;

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }
  
  public String getUname() {
    return this.uname;
  }
  
  public void setUname(final String uname) {
    this.uname = uname;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(final String pwd) {
    this.pwd = pwd;
  }

  public String getPwdConfirm() {
    return pwdConfirm;
  }

  public void setPwdConfirm(final String pwdConfirm) {
    this.pwdConfirm = pwdConfirm;
  }

  public String toString() {
    return "User(Email: " + this.email + ", Username: " + this.uname;
  }

}
