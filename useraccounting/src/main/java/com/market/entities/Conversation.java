package com.market.entities;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
  
  private User requestor;
  private User contributor;
  private List<Chatmessage> messagesRequestor;
  private List<Chatmessage> messagesContributor;
  
  /**
   * Represents a conversation between two users.
   * @param requestor The user who has been  initiated the Request
   * @param contributor The user who is willing to contribute to the Request
   */
  public Conversation(final User requestor, final User contributor) {
    
    this.requestor = requestor;
    this.contributor = contributor;
    this.messagesRequestor = new ArrayList<>();
    this.messagesContributor = new ArrayList<>();
    
  }

  public User getRequestor() {
    return requestor;
  }

  public void setRequestor(final User requestor) {
    this.requestor = requestor;
  }

  public User getContributor() {
    return contributor;
  }

  public void setContributor(final User contributor) {
    this.contributor = contributor;
  }

  public List<Chatmessage> getMessagesRequestor() {
    return messagesRequestor;
  }

  public void setMessagesRequestor(final List<Chatmessage> messagesRequestor) {
    this.messagesRequestor = messagesRequestor;
  }

  public List<Chatmessage> getMessagesContributor() {
    return messagesContributor;
  }

  public void setMessagesContributor(final List<Chatmessage> messagesContributor) {
    this.messagesContributor = messagesContributor;
  }

}
