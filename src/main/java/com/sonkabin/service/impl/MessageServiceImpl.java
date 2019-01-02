package com.sonkabin.service.impl;

import com.sonkabin.entity.Message;
import com.sonkabin.repository.MessageRepository;
import com.sonkabin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public long count(Integer id) {
        return messageRepository.countByUserId(id);
    }

    @Override
    public List<Message> findAllById(Integer id) {

        return messageRepository.findAllMsgById(id);
    }

    @Override
    public void saveMsg(Message message) {

    }

    @Transactional(readOnly = false)
    @Override
    public void marked(Integer id) {
        messageRepository.marked(id);
    }

    @Override
    public void deleteMsg(Integer id) {
        messageRepository.deleteById(id);
    }


}
