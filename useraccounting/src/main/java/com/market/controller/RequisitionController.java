package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class RequisitionController {

 
  /**
   * show the requisition-page.
   * 
   * @return requisition.html
   */
  @GetMapping(value = "/requisition")
  public String showContribution() {
    return "requisition";
  }

}

