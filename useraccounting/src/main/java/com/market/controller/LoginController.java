package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
  public String login(Model model, String error, String logout) {
      if (error != null)
          model.addAttribute("error", "Your username and password is invalid.");

      if (logout != null)
          model.addAttribute("message", "You have been logged out successfully.");

      return "login";
  }
  
  
  @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
  public String welcome(Model model) {
      return "welcome";
  }

  
  

}
