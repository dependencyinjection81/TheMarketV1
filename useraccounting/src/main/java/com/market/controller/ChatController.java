package com.market.controller;

import com.market.entities.ChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


/**
 * These methods will be responsible for receiving messages from one client and then broadcasting it to others.
 * From the websocket configuration, all the messages sent from clients with a destination starting with
 * /app will be routed to these message handling methods annotated with @MessageMapping.
 *
 * For example a message with destination /app/chat.sendMessage will be routed to the sendMessage() method,
 * and a message with destination /app/chat.addUser will be routed to the addUser() method.
 */
@Controller
public class ChatController {


  /**
   * Send a chat message.
   * @param chatMessage Chat message entity
   * @return chat message
   */
  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/public")
  public ChatMessage sendMessage(final @Payload ChatMessage chatMessage) {
    return chatMessage;
  }

  /**
   * Adds a new user to the chat.
   * @param chatMessage Chat message
   * @param headerAccessor Header accessor
   * @return chat message
   */
  @MessageMapping("/chat.addUser")
  @SendTo("/topic/public")
  public ChatMessage addUser(final @Payload ChatMessage chatMessage,
                             final SimpMessageHeaderAccessor headerAccessor) {

    /**
     * TODO only authenticated users allowed
     * Add username in web socket session
     */
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    return chatMessage;
  }

}
