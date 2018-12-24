package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {

 
  /**
   * show the welcome-page.
   * 
   * @return welcome.html
   */
  @GetMapping(value = "/welcome")
  public String showWelcome() {
    return "welcome";
  }

}
