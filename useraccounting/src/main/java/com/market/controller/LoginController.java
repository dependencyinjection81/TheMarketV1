package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class LoginController implements WebMvcConfigurer {

  /**
   * 
   * 
   * @return login.html
   */

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String showForm() {
      if (error != null)
          model.addAttribute("error", "Your username and password is invalid.");
  
  @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
  public String welcome(Model model) {
  
  

}
