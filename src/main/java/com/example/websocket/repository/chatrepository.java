package com.example.websocket.repository;

import com.example.websocket.model.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface chatrepository extends MongoRepository<chat, String> {
    List<chat> findByChatId(String chatId);
}
