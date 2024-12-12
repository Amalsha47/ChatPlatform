package com.example.websocket.controller;

import com.example.websocket.service.userservice;
import com.example.websocket.model.user;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class usercontroller {

    private final userservice userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public user addUser(
            @Payload user user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public user disconnectUser(
            @Payload user user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<user>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
