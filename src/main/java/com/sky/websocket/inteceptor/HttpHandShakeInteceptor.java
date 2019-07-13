package com.sky.websocket.inteceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpHandShakeInteceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("---------------beforeHandshake start---------------");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            String sessionId = servletServerHttpRequest.getServletRequest().getSession().getId();
            System.out.println("beforeHandshake sessionid:" + sessionId);
            attributes.put("sessionIdFirst",sessionId);
        }
        System.out.println("---------------beforeHandshake end---------------");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("---------------afterHandshake start---------------");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            System.out.println("afterHandshake sessionid:" + servletServerHttpRequest.getServletRequest().getSession().getId());
        }
        System.out.println("---------------afterHandshake end---------------");
    }
}
