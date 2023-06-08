package com.ruzhkov.jwt.backend.repositories;

import com.ruzhkov.jwt.backend.entites.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
