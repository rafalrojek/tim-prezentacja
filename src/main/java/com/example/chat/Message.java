package com.example.chat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Message {

    private @Id @GeneratedValue Long id;
    @NotNull private MessageType type;
    private String content;
    @NotBlank private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public Message() {
    }

    public Message(MessageType type, String content, String sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
    }
}
