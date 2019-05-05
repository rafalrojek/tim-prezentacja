package com.example.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class ChatController {

    private final MessageService service;

    public ChatController(MessageService service) {
        this.service = service;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message send (@Valid @Payload Message message) {
        return this.service.create(message);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Valid @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return this.service.create(message);
    }
}
