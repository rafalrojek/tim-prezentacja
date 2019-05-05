package com.example.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final MessageRepository repository;

    public ChatController(MessageRepository repository) {
        this.repository = repository;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message send (@Payload Message message) {
        return repository.save(message);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return repository.save(message);
    }
}
