package com.adminportal.service.impl;

import com.adminportal.domain.Message;
import com.adminportal.repository.MessageRepository;
import com.adminportal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-16
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> messageList() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Message findOne(Integer id) {
        return messageRepository.findOne(id);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }
}
