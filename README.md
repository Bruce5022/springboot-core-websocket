# 最初简单的websocket 例子
# 一.增加监听器
websocket提供了很多event事件,有:</br>
1.正向流程:</br>
```
sessionconnectevent---->sessionconnectedevent---->sessionsubscribeevent</br>
```
2.反向流程(可能直接关闭连接,所有的订阅自动关闭):</br>
```
sessionunsubscribeevent---->sessiondisconnectevent</br>
```
3.使用步骤:</br>
```
@Component
public class ConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {
    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        System.out.println("sessionConnectedEvent");
    }
}
```
# 二.增加handshake拦截器
1.拦截器是在建立websocket连接前的确认逻辑,实现下面接口:</br>
```
HandshakeInterceptor
```
2.需要配置:</br>
```
registry.addEndpoint("/endpoint-websocket").addInterceptors(new HttpHandlerShakeInteceptor()).setAllowedOrigins("*").withSockJS();
```
3.结论:</br>
websocket可以获取到http协议的sessionid,也就是ws的session和http的session是同一个;</br>
先走拦截器,再走其它事件,可以在其它事件中,获取拦截器放置的属性;</br>
# 三.增加channel拦截器
1.channel拦截器是实现channel监控的,实现下面接口:</br>
```
ChannelInterceptor
```
2.需要配置:</br>
```
@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new SocketChannelInteceptor());
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.interceptors(new SocketChannelInteceptor());
	}
```
3.结论(连接发送一条消息):</br>
```
---------------beforeHandshake start---------------
beforeHandshake sessionid:392D85EB219CBF8B12CC5400B9763F38
---------------beforeHandshake end---------------
---------------afterHandshake start---------------
afterHandshake sessionid:392D85EB219CBF8B12CC5400B9763F38
---------------afterHandshake end---------------
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
---------------订阅事件start---------------
订阅事件{"modified":true,"message":null,"host":null,"contentLength":null,"contentType":null,"version":null,"nack":null,"ack":null,"receiptId":null,"messageId":null,"receipt":null,"heartbeat":[0,0],"login":null,"destination":"/chat/demo_chat","subscriptionId":"sub-0","acceptVersion":[],"command":"SUBSCRIBE","passcode":null,"user":null,"sessionAttributes":{"sessionIdFirst":"392D85EB219CBF8B12CC5400B9763F38"},"sessionId":"vpl21ttm","messageType":"SUBSCRIBE","id":null,"timestamp":null,"mutable":true,"messageHeaders":{"simpMessageType":"SUBSCRIBE","stompCommand":"SUBSCRIBE","nativeHeaders":{"id":["sub-0"],"destination":["/chat/demo_chat"]},"simpSessionAttributes":{"sessionIdFirst":"392D85EB219CBF8B12CC5400B9763F38"},"simpHeartbeat":[0,0],"simpSubscriptionId":"sub-0","simpSessionId":"vpl21ttm","simpDestination":"/chat/demo_chat"},"errorChannel":null,"replyChannel":null}
订阅事件{"sessionIdFirst":"392D85EB219CBF8B12CC5400B9763F38"}
---------------订阅事件end---------------
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
com.sky.websocket.controller.DemoController@2b5183ec
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
```
关闭连接:
```
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
-->Channel preSend
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
-->Channel postSend
-->Channel afterSendCompletion
-->Channel preSend
-->Channel postSend
-->Channel afterSendCompletion
```