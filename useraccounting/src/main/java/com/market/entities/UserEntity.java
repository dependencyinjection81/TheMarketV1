package com.market.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class UserEntity {

  /**
   * Id for use in Database.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column
  private String userName;

  @OneToMany(mappedBy = "userEntity")
  private Set<RequestEntity> requests = new HashSet<RequestEntity>();

  public Set<RequestEntity> getRequests() {
    return requests;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setRequests(Set<RequestEntity> requests) {
    this.requests = requests;
  }

}
