package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {
  
  /**
   * View.
   * 
   * @return the view
   */
  @RequestMapping("/index2")
  public String index() {
    return "index2";
  }
  
}
