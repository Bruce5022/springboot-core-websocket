package com.sky.websocket.inteceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * 功能描述:频道拦截器,类似管道,可以获取消息的一些meta数据
 */
public class SocketChannelInteceptor implements ChannelInterceptor {

    // 在消息被实际发送到频道之前调用
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("-->Channel preSend");
        return message;
    }

    // 发送消息调用后,立即调用
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println("-->Channel postSend");
    }

    // 在完成发送之后进行调用,不管是否有异常发生,一般用于资源清理
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        System.out.println("-->Channel afterSendCompletion");
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        System.out.println("-->Channel preReceive");
        return true;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        System.out.println("-->Channel postReceive");
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        System.out.println("-->Channel afterReceiveCompletion");
    }
}
