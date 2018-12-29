package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class ContributionController {

 
  /**
   * show the contribution-page.
   * 
   * @return contribution.html
   */
  @GetMapping(value = "/contribution")
  public String showContribution() {
    return "contribution";
  }

}
