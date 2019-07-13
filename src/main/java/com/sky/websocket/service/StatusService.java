package com.sky.websocket.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public interface StatusService {

    void statusProcess(Message<?> message, MessageChannel channel);
}
