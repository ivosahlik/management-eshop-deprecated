package com.adminportal.service;

import com.adminportal.domain.Message;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-16
 */
public interface MessageService {

    List<Message> messageList();

    Message findOne(Integer id);

    void save(Message message);
}
