package com.market.controller;

import com.market.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class OnlineUsersController {

  @Autowired
  UserService userService;

  /**
   * test.
   * 
   * @param users    Users
   * @param username Username
   * @return template-fragment
   */
  @GetMapping(value = "/online-users/{username}")
  public String showGuestList(
      final Model users, 
      final @PathVariable("username") String username) {
    
    users.addAttribute("users", userService.findAllOnlineUsers());

    return "fragments/online-users :: online-users";
    
  }

  /**
   * test.
   * 
   * @param users Users
   * @return template-fragment
   */
  @GetMapping(value = "/online-users")
  public String showGuestList(final Model users) {
    
    users.addAttribute("users", userService.findAllOnlineUsers());

    return "fragments/online-users :: online-users";
  
  }
   
}
