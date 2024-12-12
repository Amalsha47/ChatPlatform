package com.example.websocket.controller;

import com.example.websocket.service.chatservice;
import com.example.websocket.model.chat;
import com.example.websocket.model.ChatNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class chatcontroller {

        private final SimpMessagingTemplate messagingTemplate;
        private final chatservice chatMessageService;

        @MessageMapping("/chat")
        public void processMessage(@Payload chat chatMessage) {
                chat savedMsg = chatMessageService.save(chatMessage);
                messagingTemplate.convertAndSendToUser(
                                chatMessage.getRecipientId(), "/queue/messages",
                                new ChatNotification(
                                                savedMsg.getId(),
                                                savedMsg.getSenderId(),
                                                savedMsg.getRecipientId(),
                                                savedMsg.getContent()));
        }

        @GetMapping("/messages/{senderId}/{recipientId}")
        public ResponseEntity<List<chat>> findChatMessages(@PathVariable String senderId,
                        @PathVariable String recipientId) {
                return ResponseEntity
                                .ok(chatMessageService.findChatMessages(senderId, recipientId));
        }
}
