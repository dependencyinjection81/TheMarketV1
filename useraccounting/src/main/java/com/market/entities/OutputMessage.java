package com.market.entities;

public class OutputMessage {

  private String from;
  private String text;
  private String time;
  
  /**
   * Ctor.
   * @param from Sender
   * @param text Text
   * @param time Time
   */
  public OutputMessage(final String from, final String text, final String time) {
    this.from = from;
    this.text = text;
    this.time = time;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(final String from) {
    this.from = from;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public String getTime() {
    return time;
  }

  public void setTime(final String time) {
    this.time = time;
  }

}
  

