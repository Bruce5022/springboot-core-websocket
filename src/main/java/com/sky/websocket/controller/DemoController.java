package com.sky.websocket.controller;

import com.sky.websocket.model.InMessage;
import com.sky.websocket.model.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


@Controller
public class DemoController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/sky/chat")
    public void demo(InMessage message) {
        messagingTemplate.convertAndSend("/chat/demo_chat", new OutMessage(message.getContent()));
    }

    @Scheduled(fixedRate=5000)
    public void autoSend() {
        messagingTemplate.convertAndSend("/chat/demo_chat", new OutMessage("-----hello------"));
    }

}



