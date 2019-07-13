package com.sky.websocket.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Map;

@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {

        try {
            System.out.println("---------------订阅事件start---------------");
            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
            System.out.println("订阅事件" + objectMapper.writeValueAsString(accessor));
            Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
            System.out.println("订阅事件" + objectMapper.writeValueAsString(sessionAttributes));
            System.out.println("---------------订阅事件end---------------");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
