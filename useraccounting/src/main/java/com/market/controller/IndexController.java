package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "/")
public class IndexController {
  
  /**
   * show the index page.
   * 
   * @return index.html
   */
  @GetMapping(value = "/index")
  public String index() {
    return "index";
  }
  
}
