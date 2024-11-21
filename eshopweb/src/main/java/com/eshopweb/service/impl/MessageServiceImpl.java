package com.eshopweb.service.impl;

import com.eshopweb.domain.Message;
import com.eshopweb.repository.MessageRepository;
import com.eshopweb.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-12
 */
@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Optional<Message> getMessages() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .filter(Message::isActive)
                .filter(Message::isShowAlert)
                .findFirst();
    }
}
