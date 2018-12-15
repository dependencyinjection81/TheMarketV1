package com.market.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

  /************************************************************
   * Unique ID.************************************************
   ************************************************************
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public Role() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /************************************************************
   * Role name. ************************************************
   ************************************************************
   */
  private String name;

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  /************************************************************
   * Users. ***************************************************
   ************************************************************
   */
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;
  
  public Set<User> getUsers() {
    return this.users;
  }

  public void setUsers(final Set<User> users) {
    this.users = users;
  }

}
