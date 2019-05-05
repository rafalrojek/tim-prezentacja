package com.example.chat;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> getAll() {
        return repository.findAll();
    }

    @Override
    public Message get(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
    }

    @Override
    public Message create(Message message) {
        return this.repository.save(message);
    }

    @Override
    public Message update(Message newMessage, Long id) {
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

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
