package com.market.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "chatmessage")
public class Chatmessage {
  
  
  /************************************************************
   * Unique ID for each chatmessage.***************************
   ************************************************************
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id;
  
  @Column(updatable = false, nullable = false)
  private Long userIdFrom;
  
  @Column(updatable = false, nullable = false)
  private Long userIdTo;
  
  @Column(updatable = false, nullable = false)
  private Date timestamp;
  
  @Column(updatable = false, nullable = false)
  private String content;

  /**
   * Represents a single chatmessage.
   * @param userIdFrom Sender
   * @param userIdTo Reciever
   * @param timestamp Timestamp
   * @param content Content
   */
  public Chatmessage(
      final Long userIdFrom, 
      final Long userIdTo, 
      final Date timestamp, 
      final String content) {
    
    this.userIdFrom = userIdFrom;
    this.userIdTo = userIdTo;
    this.timestamp = timestamp;
    this.content = content;
    
  }


  /******getters and setters.*****/

  public Long getFrom() {
    return this.userIdFrom;
  }

  public void setFrom(final Long userIdFrom) {
    this.userIdFrom = userIdFrom;
  }

  public Long getTo() {
    return this.userIdTo;
  }

  public void setTo(final Long userIdTo) {
    this.userIdTo = userIdTo;
  }

  public Date getDate() {
    return this.timestamp;
  }

  public void setDate(final Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return this.content;
  }

  public void setMessage(final String content) {
    this.content = content;
  }
  
}
