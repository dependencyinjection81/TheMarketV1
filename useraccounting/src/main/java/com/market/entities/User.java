package com.market.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  /**
   * Ctor.
   * @param id ID
   * @param userName Username
   * @param password Password
   * @param roles Roles
   */
  public User(
      final long id, 
      final String userName, 
      final String password, 
      final Set<Role> roles) {
    
    this.id = id;
    this.username = userName;
    this.password = password;
    this.roles = roles;
    this.online = false;
  }
  
  /**
   * Default ctor.
   * Only called when a new user entity will be created during the registration process.
   */
  public User() {
    super();
    this.enabled = false;
    this.online = false;
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
   * User-online?.**********************************************
   ************************************************************
   */
  @Column(name = "online")
  private boolean online;

  public boolean getOnline() {
    return this.online;
  }

  public void setOnline(final boolean value) {
    this.online = value;
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
  @JoinTable(
      name = "user_role", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(final Set<Role> roles) {
    this.roles = roles;
  }
  
  /************************************************************
   * Requests.*************************************************
   ************************************************************
   */
  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<UserRequest> requests = new ArrayList<>();
  
  public List<UserRequest> getRequests() {
    return requests;
  }
  
  public void setRequests(final List<UserRequest> requests) {
    this.requests = requests;
  }
  
  public void addRequest(final UserRequest request) {
    requests.add(request);
    request.setUser(this);
  }

  public void removeRequest(final UserRequest request) {
    requests.remove(request);
    request.setUser(null);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (enabled ? 1231 : 1237);
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + (online ? 1231 : 1237);
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((requests == null) ? 0 : requests.hashCode());
    result = prime * result + ((roles == null) ? 0 : roles.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    User other = (User) obj;
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (enabled != other.enabled) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (online != other.online) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!password.equals(other.password)) {
      return false;
    }
    if (requests == null) {
      if (other.requests != null) {
        return false;
      }
    } else if (!requests.equals(other.requests)) {
      return false;
    }
    if (roles == null) {
      if (other.roles != null) {
        return false;
      }
    } else if (!roles.equals(other.roles)) {
      return false;
    }
    if (username == null) {
      if (other.username != null) {
        return false;
      }
    } else if (!username.equals(other.username)) {
      return false;
    }
    return true;
  }

}
