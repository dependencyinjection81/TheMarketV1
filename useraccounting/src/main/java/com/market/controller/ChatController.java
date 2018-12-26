package com.market.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
@RequestMapping(value = "/")
public class ChatController implements WebMvcConfigurer {
  
  public String message;

  /**
   * Listen for incoming messages.
   * @param message Message
   * @return
   */
  @PostMapping(value = "/send-message", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody String postRequest(final @RequestParam("message") String message) {
    
    // TODO create a new message entity
    // TODO store message entity to db
    
    this.message = message;
    return "Message sent";
  }
  
  
  @GetMapping(value = "/update-conversation")
  public String updateChat() {
    return "fragments/chatbox :: chatbox";
  }

}