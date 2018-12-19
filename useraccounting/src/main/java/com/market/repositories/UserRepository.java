package com.market.repositories;

import com.market.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(final String userName);
  
  User findByEmail(final String email);
  
  /**
   * Find all online users.
   * @return Users
   */
  @Query(
      value = "SELECT user.username FROM user WHERE user.online = 1", 
      nativeQuery = true)
  List<String> findAllOnlineUsers();
  
  /**
   * Set user online by email.
   * @param email Email
   */
  @Transactional
  @Modifying
  @Query(
      value = "UPDATE user SET user.online = 1 WHERE user.email = ?1", 
      nativeQuery = true)
  void setUserOnline(final String email);
  
  /**
   * Set user offline by email.
   * @param email Email
   */
  @Transactional
  @Modifying
  @Query(
      value = "UPDATE user SET user.online = 0 WHERE user.email = ?1", 
      nativeQuery = true)
  void setUserOffline(final String email);
 
}
