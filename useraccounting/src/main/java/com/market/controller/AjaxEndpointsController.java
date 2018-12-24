package com.market.controller;

import com.market.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class AjaxEndpointsController {

  @Autowired
  UserService userService;

  /**
   * test.
   * 
   * @param users    Users
   * @param username Username
   * @return template-fragment
   */
  @RequestMapping(value = "/online-users/{username}", method = RequestMethod.GET)
  public String showGuestList(
      final Model users, 
      final @PathVariable("username") String username) {
    
    users.addAttribute("users", userService.findAllOnlineUsers());

    return "online-users :: online-users";
    
  }

  /**
   * test.
   * 
   * @param users Users
   * @return template-fragment
   */
  @RequestMapping(value = "/online-users", method = RequestMethod.GET)
  public String showGuestList(final Model users) {
    
    users.addAttribute("users", userService.findAllOnlineUsers());

    return "online-users :: online-users";
  
  }

}
