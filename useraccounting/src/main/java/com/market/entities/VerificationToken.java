package com.market.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Verification Token which is linked back to the User via an unidirectional relation.
 * The token will have an unique, randomly generated value.
 * @author Johannes Weiss
 *
 */
@Entity
public class VerificationToken {

  /**
   * Expiration time in minutes.
   */
  private static final int EXPIRATION = 60 * 24;

  /**
   * Unique generated Id of the token.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * The actual token.
   */
  private String token;

  /**
   * The User the token is related to.
   * FetchType EAGER is the pendant to LAZY
   * It means that the userentity will defenitly loaded together with
   * the verification token.
   */
  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
  private User user;

  /**
   * Absolute expiration date of the token.
   */
  private Date expiryDate;

  /**
   * Default ctor.
   */
  public VerificationToken() {
    super();
  }

  /**
   * Ctor.
   * @param token Token
   */
  public VerificationToken(final String token) {
    super();

    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  /**
   * Custom ctor.
   * @param token actual token.
   * @param user user the token is related to.
   */
  public VerificationToken(final String token, final User user) {
    super();

    this.token = token;
    this.user = user;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  public Long getId() {
    return this.id;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(final String token) {
    this.token = token;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(final User user) {
    this.user = user;
  }

  public Date getExpiryDate() {
    return this.expiryDate;
  }

  public void setExpiryDate(final Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  /**
   * Update an exsiting Token.
   * @param token new generated token from elsewhere to update this token.
   */
  public void updateToken(final String token) {
    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

  
  private Date calculateExpiryDate(final int expiryTimeInMinutes) {
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(new Date().getTime());
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
    result = prime * result + ((token == null) ? 0 : token.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final VerificationToken other = (VerificationToken) obj;
    if (expiryDate == null) {
      if (other.expiryDate != null) {
        return false;
      }
    } else if (!expiryDate.equals(other.expiryDate)) {
      return false;
    }
    if (token == null) {
      if (other.token != null) {
        return false;
      }
    } else if (!token.equals(other.token)) {
      return false;
    }
    if (user == null) {
      if (other.user != null) {
        return false;
      }
    } else if (!user.equals(other.user)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate)
        .append("]");
    return builder.toString();
  }

}