package com.sky.websocket.service.impl;

import com.sky.websocket.service.StatusService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public class StatusServiceImpl implements StatusService {
    @Override
    public void statusProcess(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if (accessor == null || accessor.isHeartbeat()) return;
        String sessionId = accessor.getSessionId();
        System.out.println("websocket客户端sessionid：" + sessionId);
        Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
        if (!CollectionUtils.isEmpty(sessionAttributes)) {
            System.out.println("http客户端sessionid：" + sessionAttributes.get("sessionId"));
        }
        if (accessor.getCommand() == null) return;

        switch (accessor.getCommand()) {
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:
                subscribe(sessionId);
                break;
            case UNSUBSCRIBE:
                unsubscribe(sessionId);
                break;
            case SEND:
                send(sessionId);
                break;
            case ACK:
                ack(sessionId);
                break;
            case NACK:
                nack(sessionId);
                break;
            case BEGIN:
                begin(sessionId);
                break;
            case COMMIT:
                commit(sessionId);
                break;
            case ABORT:
                abort(sessionId);
                break;
            case CONNECTED:
                connected(sessionId);
                break;
            case RECEIPT:
                receipt(sessionId);
                break;
            case MESSAGE:
                message(sessionId);
                break;
            default:
                others(sessionId);
                break;
        }
    }

    private static void connect(String sessionId) {
        System.out.println("客户端-connect：" + sessionId);
    }

    private static void subscribe(String sessionId) {
        System.out.println("客户端-subscribe：" + sessionId);
    }

    private static void disconnect(String sessionId) {
        System.out.println("客户端-disconnect：" + sessionId);
    }

    private static void unsubscribe(String sessionId) {
        System.out.println("客户端-unsubscribe：" + sessionId);
    }

    private static void send(String sessionId) {
        System.out.println("客户端-send：" + sessionId);
    }

    private static void ack(String sessionId) {
        System.out.println("客户端-ack：" + sessionId);
    }

    private static void nack(String sessionId) {
        System.out.println("客户端-nack：" + sessionId);
    }

    private static void begin(String sessionId) {
        System.out.println("客户端-begin：" + sessionId);
    }

    private static void commit(String sessionId) {
        System.out.println("客户端-commit：" + sessionId);
    }

    private static void abort(String sessionId) {
        System.out.println("客户端-abort：" + sessionId);
    }

    private static void connected(String sessionId) {
        System.out.println("客户端-connected：" + sessionId);
    }

    private static void receipt(String sessionId) {
        System.out.println("客户端-receipt：" + sessionId);
    }

    private static void message(String sessionId) {
        System.out.println("客户端-message：" + sessionId);
    }

    private static void error(String sessionId) {
        System.out.println("客户端-error：" + sessionId);
    }

    private static void others(String sessionId) {
        System.out.println("客户端-others：" + sessionId);
    }

}
