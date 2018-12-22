package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class DragAndDropController implements WebMvcConfigurer {

  
  @GetMapping(value = "/drag-and-drop")
  public String showLogin() {
    return "drag-and-drop01";
  }
 
}
