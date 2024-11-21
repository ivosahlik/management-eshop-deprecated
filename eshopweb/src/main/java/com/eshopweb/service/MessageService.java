package com.eshopweb.service;

import com.eshopweb.domain.Message;

import java.util.Optional;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-12
 */
public interface MessageService {

    Optional<Message> getMessages();

}
