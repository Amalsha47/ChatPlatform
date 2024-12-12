package com.example.websocket.repository;

import com.example.websocket.model.user;
import com.example.websocket.model.Status;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepository extends MongoRepository<user, String> {
    List<user> findAllByStatus(Status status);
}