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
