package com.market.repositories;

import com.market.entities.VerificationToken;

import java.sql.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(final String token);

  Stream<VerificationToken> findAllByExpiryDateLessThan(final Date now);

  void deleteByExpiryDateLessThan(final Date now);

  /**
   * Delete expired verification tokens.
   * TODO make use of this randomly
   * @param now date now
   */
  @Modifying
  @Query("delete from VerificationToken t where t.expiryDate <= ?1")
  void deleteAllExpiredSince(final Date now);
}
