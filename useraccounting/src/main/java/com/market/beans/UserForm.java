package com.market.beans;

import com.market.security.constraint.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FieldMatch(first = "pwd", second = "pwdConfirm", 
    message = "{UserForm.pwdconfirm.FieldMatch.message}")
public class UserForm {
  
  
  private final Logger log = LoggerFactory.getLogger(this.getClass());

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

  public String getUname() {
    log.debug("getUname() returned: " + uname);
    return this.uname;
  }
  
  public void setUname(final String uname) {
    log.debug(uname + " :has been set");
    this.uname = uname;
  }
  
  
  public String getEmail() {
    log.debug("getEmail() returned: " + email);
    return email;
  }

  public void setEmail(final String email) {
    log.debug(email + " :has been set");
    this.email = email;
  }
  
  public String getPwd() {
    log.debug("getPwd() returned: " + pwd);
    return pwd;
  }

  public void setPwd(final String pwd) {
    log.debug(pwd + " :has been set");
    this.pwd = pwd;
  }

  public String getPwdConfirm() {
    log.debug("getPwdConfirm() returned: " + pwdConfirm);
    return pwdConfirm;
  }

  public void setPwdConfirm(final String pwdConfirm) {
    log.debug(pwdConfirm + " :has been set");
    this.pwdConfirm = pwdConfirm;
  }

  public String toString() {
    return "User(Email: " + this.email + ", Username: " + this.uname;
  }

}
