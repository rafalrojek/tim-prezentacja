package com.example.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MessageController {

    private final MessageService service;

    public MessageController(MessageServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/messages")
    List<Message> getAll() {
        return this.service.getAll();
    }

    @PostMapping("/messages")
    Message add(@Valid @RequestBody Message message) {
        return this.service.create(message);
    }

    @GetMapping("/messages/{id}")
    Message get(@PathVariable Long id) {
        return this.service.get(id);
    }

    @PutMapping("/messages/{id}")
    Message replace (@Valid @RequestBody Message newMessage, @PathVariable Long id) {
        return this.service.update(newMessage, id);
    }

    @DeleteMapping("/messages/{id}")
    void delete (@PathVariable Long id) {
        this.service.delete(id);
    }
}
