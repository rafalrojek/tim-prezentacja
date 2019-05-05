package com.example.chat;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("Message " + id + " doesn't exist");
    }
}
