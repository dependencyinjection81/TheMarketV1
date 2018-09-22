package com.market.repositories;

import java.sql.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.market.entities.User;
import com.market.entities.VerificationToken;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);

  VerificationToken findByUser(User user);

  Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);

  void deleteByExpiryDateLessThan(Date now);

  @Modifying
  @Query("delete from VerificationToken t where t.expiryDate <= ?1")
  void deleteAllExpiredSince(Date now);
}
