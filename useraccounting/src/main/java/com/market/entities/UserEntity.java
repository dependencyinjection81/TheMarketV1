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

  public UserEntity(final long id, final String userName, final String password, final String role) {
    this.id = id;
    this.username = userName;
    this.password = password;
    this.role = role;
  }
  
  public UserEntity() {
    
  }
  
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

  public void setId(final Long id) {
    this.id = id;
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
  private String username;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(final String username) {
    this.username = username;
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
  private String password;

  public String getPassword() {
    return this.password;
  }

  public void setPassword(final String pwd) {
    this.password = pwd;
  }
  
  /************************************************************
   * Role.*****************************************************
   ************************************************************
   */
  
  private String role;

  public String getRole() {
    return role;
  }

  public void setRole(final String role) {
    this.role = role;
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
}
