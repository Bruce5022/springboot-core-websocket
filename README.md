# 最初简单的websocket 例子
#增加监听器
websocket提供了很多event事件,有:</br>
1.正向流程</br>
sessionconnectevent---->sessionconnectedevent---->sessionsubscribeevent</br>
2.反向流程(可能直接关闭连接,所有的订阅自动关闭)</br>
sessionunsubscribeevent---->sessiondisconnectevent</br>
3.使用步骤</br>
@Component</br>
public class ConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {</br>
    @Override</br>
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {</br>
        System.out.println("sessionConnectedEvent");</br>
    }</br>
}</br>
#增加拦截器</br>

