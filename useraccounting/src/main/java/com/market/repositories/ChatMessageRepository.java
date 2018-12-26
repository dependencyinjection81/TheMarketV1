package com.market.repositories;

import com.market.entities.Chatmessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("chatMessageRepository")
public interface ChatMessageRepository extends JpaRepository<Chatmessage, Long> {
  

}
