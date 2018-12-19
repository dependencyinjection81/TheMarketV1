package com.market.controller;

import com.market.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {

  @Autowired
  UserService userService;

  /**
   * show the welcome-page.
   * 
   * @return welcome.html
   */
  @GetMapping(value = "/welcome")
  public String showWelcome(Model users) {
    users.addAttribute("users", userService.findAllOnlineUsers());
    return "welcome";
  }

}
