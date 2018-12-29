package com.market.beans;

public class RequestForm {
  
  private String title;
  private String text;
  private String location;
  private String urgency;
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(final String title) {
    this.title = title;
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(final String text) {
    this.text = text;
  }
  
  public String getLocation() {
    return location;
  }
  
  public void setLocation(final String location) {
    this.location = location;
  }
  
  public String getUrgency() {
    return urgency;
  }
  
  public void setUrgency(final String urgency) {
    this.urgency = urgency;
  }

}
