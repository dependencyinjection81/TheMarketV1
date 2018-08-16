package com.market.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
/* @Immutable */
public class UserEntity {

  /************************************************************
   * Unique ID for each user.**********************************
   ************************************************************
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId() {
    this.id = generateId();
  }

  /************************************************************
   * Confirmation-Token.***************************************
   ************************************************************
   */
  @Column(name = "confirmation_token")
  private String confirmationToken;

  public String getConfirmationToken() {
    return this.confirmationToken;
  }

  public void setConfirmationToken(final String confirmationToken) {
    this.confirmationToken = confirmationToken;
  }

  /************************************************************
   * User-status.**********************************************
   ************************************************************
   */
  @Column(name = "enabled")
  private boolean enabled;

  public boolean getEnabled() {
    return this.enabled;
  }

  public void setEnabled(final boolean value) {
    this.enabled = value;
  }

  /************************************************************
   * Username.*************************************************
   ************************************************************
   */
  @Column
  private String userName;

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(final String userName) {
    this.userName = userName;
  }

  /************************************************************
   * Email.****************************************************
   ************************************************************
   */
  @Column
  private String email;

  public String getEmail() {
    return this.email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  /************************************************************
   * Password.*************************************************
   ************************************************************
   */
  @Column
  private String pwd;

  public String getPwd() {
    return this.pwd;
  }

  public void setPwd(final String pwd) {
    this.pwd = pwd;
  }

  /************************************************************
   * Requests of each user.************************************
   ************************************************************
   */
  @OneToMany(mappedBy = "userEntity")
  private Set<RequestEntity> requests = new HashSet<RequestEntity>();

  public void setRequests(final Set<RequestEntity> requests) {
    this.requests = requests;
  }

  public Set<RequestEntity> getRequests() {
    return requests;
  }

  
  /************************************************************
   * ID Generator.*********************************************
   ************************************************************
   */
  private Long generateId() {
    return 123L;
  }

}
