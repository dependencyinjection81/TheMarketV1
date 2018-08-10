package com.market.beans;

import com.market.security.constraint.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch(first = "pwd", second = "pwdConfirm", 
    message = "{UserForm.pwdconfirm.FieldMatch.message}")
public class UserForm {

  @NotBlank(message = "{UserForm.uname.NotBlank.message}")
  private String uname;
  
  @NotBlank(message = "{UserForm.email.NotBlank.message}")
  @Email(message = "{UserForm.email.Email.message}")
  private String email;
  
  @NotBlank(message = "{UserForm.pwd.NotBlank.message}")
  @Size(min = 8, message = "{UserForm.pwd.Size.message}")
  private String pwd;

  @NotBlank(message = "{UserForm.pwdconfirm.NotBlank.message}")
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
