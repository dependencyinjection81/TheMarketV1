package com.market.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

  /**
   * Custom Ctor.
   * @param id unique user id
   * @param userName username
   * @param password password
   * @param role role
   */
  public User(final long id, 
      final String userName, final String password, final Set<Role> roles) {
    this.id = id;
    this.username = userName;
    this.password = password;
    this.roles = roles;
  }
  
  /**
   * Default ctor.
   * Only called when a new user entity will be created during the registration process.
   */
  public User() {
    super();
    this.enabled=false;
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
  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(final Set<Role> roles) {
    this.roles = roles;
  }

}
