package com.market.service;

import com.market.entities.Chatmessage;
import com.market.entities.User;
import com.market.repositories.ChatMessageRepository;
import com.market.repositories.UserRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ChatService {
  
  @Autowired
  private ChatMessageRepository chatMessageRepository;
  
  // TODO mir gefällt noch nicht, dass ich user und chat repository hier drin hab
  @Autowired
  private UserRepository userRepository;
  
  /**
   * TODO this is just an very ugly workaround for now
   * Creates a new message entity from available parameters.
   * @param userIdFrom ID of sender
   * @param usernameTo Username of reciever
   * @param content Message content
   */
  public void createNewChatMessage(
      final Long userIdFrom, 
      final String usernameTo, 
      final String content) {
  
    User userTo = userRepository.findByUsername(usernameTo);
    Long userIdTo = userTo.getId();
    Date timestamp = new Date();
    Chatmessage chatmessage = new Chatmessage(userIdFrom, userIdTo, timestamp, content);
    saveChatMessage(chatmessage);
    
    
  }
  
  private void saveChatMessage(final Chatmessage chatmessage) {   
    chatMessageRepository.save(chatmessage);    
  }
  
}
