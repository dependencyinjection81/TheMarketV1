package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class LoginController implements WebMvcConfigurer {

  /**
   * show the login-page and binds the validation bean to it.
   * 
   * @return login.html
   */

  @GetMapping(value = "/login")
  public String showForm() {
    return "login";
  }
  
}
