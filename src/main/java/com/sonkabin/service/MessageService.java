package com.sonkabin.service;

import com.sonkabin.entity.Message;

import java.util.List;

public interface MessageService {
    long count(Integer id);
    
    List<Message> findAllById(Integer id);

    void saveMsg(Message message);

    void marked(Integer id);

    void deleteMsg(Integer id);
}
