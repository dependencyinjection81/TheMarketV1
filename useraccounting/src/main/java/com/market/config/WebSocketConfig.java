package com.market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * This will be the endpoint the client connects to.
   * STOMP stands for Simple Text Oriented Messaging Protocol
   * and is implemented by Spring
   */
  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").withSockJS();
  }

  /**
   * Configures the message broker
   * The first line defines that the messages whose destination starts with
   * “/app” should be routed to message-handling methods
   *
   * The second line defines that the messages whose destination starts with “/topic” should be routed to the message
   * broker. Message broker broadcasts messages to all the connected clients who are subscribed to a particular topic.
   * @param registry Registry
   */
  @Override
  public void configureMessageBroker(final MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/app");
    registry.enableSimpleBroker("/topic");
  }


}
