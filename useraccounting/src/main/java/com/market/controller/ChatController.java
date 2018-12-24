package com.market.controller;

import com.market.beans.MessageForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class ChatController implements WebMvcConfigurer {

  /**
   * Recieve incoming chat-messages to send to a specific user.
   * 
   * @param messageForm   Message form
   * @param bindingResult Binding result
   * @param action        send-message action
   * @param request       Request
   */
  @PostMapping(value = "/send-message")
  public String processForm(final MessageForm messageForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action,
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) {
  
    System.out.println(messageForm.getMessage());
    return "welcome";
    
  }
}
