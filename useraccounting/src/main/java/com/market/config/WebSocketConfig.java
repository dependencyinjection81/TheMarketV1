package com.market.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Enables WebSocket message handling, backed by a message broker
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * Here, we can see that the method configureMessageBroker is used to configure 
   * the message broker.
   * First, we enable an in-memory message broker to carry the messages back to
   * the client on destinations prefixed with “/topic”.
   * @param config Configuration
   */
  @Override
  public void configureMessageBroker(final MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
    config.setApplicationDestinationPrefixes("/app");
  }

  /**
   * Registers the “/chat” endpoint and enabling Spring’s STOMP support.
   * Also adds an endpoint that works without the SockJS for the sake of elasticity.
   * This endpoint, when prefixed with “/app”,
   * is the endpoint that the ChatController.send() method is mapped to handle.
   * It also enables the SockJS fallback options,
   * so that alternative messaging options may be used if WebSockets are not available.
   * This is useful since WebSocket is not supported in all browsers yet and
   * may be precluded by restrictive network proxies.
   * The fallbacks let the applications use a WebSocket API but gracefully degrade to 
   * non-WebSocket alternatives
   * when necessary at runtime.
   * @param registry Registry
   */
  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/chat");
    registry.addEndpoint("/chat").withSockJS();
  }
}
