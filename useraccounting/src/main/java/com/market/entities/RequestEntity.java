package com.market.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class RequestEntity {

  /**
   * Id for use in Database.
   */
  @Id
  // @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @ManyToOne
  private UserEntity userEntity;

  @Column
  public String requestName;

  @Column
  public String requestText;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  public String getRequestName() {
    return requestName;
  }

  public void setRequestName(String requestName) {
    this.requestName = requestName;
  }

  public String getRequestText() {
    return requestText;
  }

  public void setRequestText(String requestText) {
    this.requestText = requestText;
  }

}
