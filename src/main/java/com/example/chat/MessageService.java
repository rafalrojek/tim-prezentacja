package com.example.chat;

import java.util.List;

public interface MessageService {

    public List<Message> getAll();
    public Message get(Long id);
    public Message create (Message message);
    public Message update (Message message, Long id);
    public void delete (Long id);
}
