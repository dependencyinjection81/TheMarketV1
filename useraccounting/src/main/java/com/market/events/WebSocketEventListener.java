package com.market.events;

import com.market.entities.ChatMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  /**
   * Since the userjoin-event has already been broadcasted in the addUser() method defined inside ChatController
   * there is no need to do anything in the SessionConnected-event.
   * @param event SessionConnectedEvent
   */
  @EventListener
  public void handleWebSocketConnectListener(final SessionConnectedEvent event) {
    logger.info("Received a new web socket connection");
  }

  /**
   * SessionDisconnectEvent extract the user’s name from the websocket session and
   * broadcast a userleave-event to all the connected clients.
   * @param event SessionDisconnectEvent
   */
  @EventListener
  public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if(username != null) {
      logger.info("User Disconnected : " + username);

      ChatMessage chatMessage = new ChatMessage();
      chatMessage.setType(ChatMessage.MessageType.LEAVE);
      chatMessage.setSender(username);

      messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
  }
}
