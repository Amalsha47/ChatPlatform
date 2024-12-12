package com.example.websocket.service;

import com.example.websocket.repository.userrepository;
import com.example.websocket.model.user;
import com.example.websocket.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class userservice {

    private final userrepository repository;

    public void saveUser(user User) {
        User.setStatus(Status.ONLINE);
        repository.save(User);
    }

    public void disconnect(user User) {
        var storedUser = repository.findById(User.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<user> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
