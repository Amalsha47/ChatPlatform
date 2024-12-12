package com.example.websocket.repository;

import com.example.websocket.model.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface chatroomrepository extends MongoRepository<chatroom, String> {
    Optional<chatroom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
