package com.market.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageForm {
    
  /************************************************************
   * Outgoing message.*****************************************
   ************************************************************
   */
  @NotBlank(message = "{MessageForm.message.NotBlank.message}")
  @Size(min = 1, max = 140, message = "{MessageForm.message.size.message}")
  private String message;
  
  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

}
