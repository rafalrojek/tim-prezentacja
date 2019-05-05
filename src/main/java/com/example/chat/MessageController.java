package com.example.chat;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/messages")
    List<Message> getAll() {
        return this.repository.findAll();
    }

    @PostMapping("/messages")
    Message add(@RequestBody Message message) {
        return this.repository.save(message);
    }

    @GetMapping("/messages/{id}")
    Message get(@PathVariable Long id) {
        return this.repository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
    }

    @PutMapping("/messages/{id}")
    Message replace (@RequestBody Message newMessage, @PathVariable Long id) {
        return this.repository.findById(id)
                .map(old -> {
                    old.setSender(newMessage.getSender());
                    old.setContent(newMessage.getContent());
                    old.setType(newMessage.getType());
                    return this.repository.save(old);
                }).orElseGet(() -> {
                    newMessage.setId(id);
                    return this.repository.save(newMessage);
                });
    }

    @DeleteMapping("/messages/{id}")
    void delete (@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}
