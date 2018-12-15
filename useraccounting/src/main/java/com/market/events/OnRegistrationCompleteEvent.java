package com.market.events;

import com.market.entities.User;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

  private String appUrl;
  private Locale locale;
  private User user;

  /**
   * Custom ctor.
   * @param user user
   * @param locale prefered locale
   * @param appUrl contextPath
   */
  public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
    super(user);
    this.user = user; 
    this.locale = locale; /*Not in use*/
    this.appUrl = appUrl; /*Not in use*/
  }


  public String getAppUrl() {
    return appUrl;
  }

  public Locale getLocale() {
    return locale;
  }

  public User getUser() {
    return user;
  }

}
